package com.bouyahya.notes

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.bouyahya.notes.navigation.LocalNavController
import com.bouyahya.notes.navigation.RootNavigationGraph

@Composable
fun App() {
    MaterialTheme {
        val navHost = rememberNavController()
        CompositionLocalProvider(LocalNavController provides navHost) {
            RootNavigationGraph()
        }
    }
}