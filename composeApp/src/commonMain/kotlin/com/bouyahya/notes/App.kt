package com.bouyahya.notes

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.bouyahya.notes.navigation.RootNavigationGraph

@Composable
fun App() {
    MaterialTheme {
        RootNavigationGraph(
            navController = rememberNavController()
        )
    }
}