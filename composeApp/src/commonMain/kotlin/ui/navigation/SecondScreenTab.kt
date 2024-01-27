package ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ui.screens.SecondScreen

object SecondScreenTab : Tab {
    @Composable
    override fun Content() {
        Navigator(SecondScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Person)
            val title = "SecondScreen"
            val index: UShort = 1u

            return TabOptions(
                index, title, icon
            )
        }
}