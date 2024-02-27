package com.bouyahya.notes.core.navigation.bottom_navigation.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.bouyahya.notes.features.profile.ui.ProfileEvent
import com.bouyahya.notes.features.profile.ui.ProfileScreen
import com.bouyahya.notes.features.profile.ui.ProfileViewModel

object ProfileScreenTab : Tab {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ProfileViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.onEvent(ProfileEvent.GetProfile)
        }

        ProfileScreen(state = state)
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Person)
            val title = "Profile"
            val index: UShort = 1u

            return TabOptions(
                index, title, icon
            )
        }
}