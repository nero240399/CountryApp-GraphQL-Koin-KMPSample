package app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.di.appModule
import feature.country.ContinentsScreen
import feature.country.CountryViewModel
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.koin.koinViewModel
import org.koin.compose.KoinApplication

@Composable
fun MyCountryApp() {
    PreComposeApp {
        KoinApplication(application = {
            modules(appModule())
        }) {
            val countryViewModel = koinViewModel<CountryViewModel>()
            val uiState by countryViewModel.uiState.collectAsState()
            LaunchedEffect(countryViewModel) {
                countryViewModel.updateContinents()
            }
            ContinentsScreen(uiState)
        }
    }
}
