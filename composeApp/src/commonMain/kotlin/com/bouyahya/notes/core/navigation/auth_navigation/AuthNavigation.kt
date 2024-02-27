package com.bouyahya.notes.core.navigation.auth_navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

object AuthNavigation : Screen {
    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {}
    }
}