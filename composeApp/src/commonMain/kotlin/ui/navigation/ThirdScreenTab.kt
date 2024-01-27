package ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ui.screens.ThirdScreen

object ThirdScreenTab : Tab {
    @Composable
    override fun Content() {
        Navigator(ThirdScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Settings)
            val title = "ThirdScreen"
            val index: UShort = 2u

            return TabOptions(
                index, title, icon
            )
        }
}