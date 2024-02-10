import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.bouyahya.notes.App
import com.bouyahya.notes.core.di.initKoin

fun main() = application {
    initKoin()
    Window(onCloseRequest = ::exitApplication, title = "Notes") {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}