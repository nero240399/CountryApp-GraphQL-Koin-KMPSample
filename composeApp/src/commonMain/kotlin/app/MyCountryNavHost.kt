package app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import feature.country.ContinentsScreen
import feature.country.CountriesScreen
import feature.country.CountryViewModel
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun MyCountryNavHost() {
    val navigator = rememberNavigator()
    val viewModel = koinViewModel<CountryViewModel>()
    NavHost(
        navigator = navigator,
        initialRoute = "/continents",
    ) {
        scene(route = "/continents") {
            val uiState by viewModel.continentsUiState.collectAsState()
            LaunchedEffect(viewModel) {
                viewModel.updateContinents()
            }
            ContinentsScreen(uiState) { continentName ->
                viewModel.selectContinent(continentName)
                navigator.navigate("/continents/$continentName")
            }
        }

        scene(route = "/continents/{name}") { _ ->
            val uiState by viewModel.countriesUiState.collectAsState()
            CountriesScreen(
                state = uiState,
                onClickBack = { navigator.goBack() },
                onClickCountry = { countryName -> viewModel.selectCountry(countryName) }
            )
        }
    }
}