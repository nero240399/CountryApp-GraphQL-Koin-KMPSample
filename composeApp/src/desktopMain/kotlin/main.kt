
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.MyCountryApp

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "My Country App") {
        MyCountryApp()
    }
}
