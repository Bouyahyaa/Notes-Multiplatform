package com.bouyahya.notes

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.bouyahya.notes.core.navigation.auth_navigation.AuthNavigation
import com.bouyahya.notes.core.navigation.bottom_navigation.BottomNavigation
import com.bouyahya.notes.core.navigation.splash.SplashScreenNav
import com.russhwolf.settings.Settings
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun App() {
    val settings = koinInject<Settings>()

    MaterialTheme {
        Navigator(SplashScreenNav) { navigator ->
            LaunchedEffect(Unit) {
                delay(1500L)
                val token = settings
                    .getString("token", "")


                // check and navigate
                if (token.isEmpty()) {
                    navigator.push(AuthNavigation)
                } else {
                    navigator.push(BottomNavigation)
                }
            }
            SlideTransition(navigator)
        }
    }
}