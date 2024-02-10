import androidx.compose.ui.window.ComposeUIViewController
import com.bouyahya.notes.App
import com.bouyahya.notes.core.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController {
        App()
    }
}
