package app

import androidx.compose.runtime.Composable
import app.di.appModule
import moe.tlaster.precompose.PreComposeApp
import org.koin.compose.KoinApplication

@Composable
fun MyCountryApp() {
    PreComposeApp {
        KoinApplication(application = {
            modules(appModule())
        }) {
            MyCountryNavHost()
        }
    }
}
