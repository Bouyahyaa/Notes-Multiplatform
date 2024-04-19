package com.bouyahya.notes.core.navigation.auth_navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition

object AuthNavigation : Screen {
    @Composable
    override fun Content() {
        Navigator(LoginScreenNav) {
            FadeTransition(it)
        }
    }
}