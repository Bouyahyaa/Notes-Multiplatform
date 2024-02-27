package com.bouyahya.notes

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.bouyahya.notes.core.navigation.bottom_navigation.BottomNavigation


@Composable
fun App() {
    MaterialTheme {
        Navigator(BottomNavigation) {
            SlideTransition(it)
        }
    }
}