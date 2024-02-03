package app

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.di.appModule
import com.apollographql.apollo3.ApolloClient
import core.data.ApolloContinentClient
import core.domain.ContinentClient
import feature.country.CountryViewModel
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.stateholder.LocalStateHolder
import org.koin.compose.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

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
            Text(uiState.continents.toString())
        }
    }
}
