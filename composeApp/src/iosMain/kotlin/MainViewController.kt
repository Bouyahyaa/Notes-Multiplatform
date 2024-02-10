import androidx.compose.ui.window.ComposeUIViewController
import com.bouyahya.notes.App
import com.bouyahya.notes.core.di.appModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {

    startKoin {
        modules(appModule)
    }

    return ComposeUIViewController {
        App()
    }
}
