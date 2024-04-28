package com.bouyahya.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bouyahya.notes.features.auth.ui.splash.SplashScreen
import com.bouyahya.notes.navigation.auth.authNavGraph
import com.bouyahya.notes.navigation.home.HomeScreen

@Composable
fun RootNavigationGraph() {
    val navController = LocalNavController.current

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") { SplashScreen() }

        authNavGraph(navController = navController)

        composable(route = Graph.HOME) { HomeScreen() }
    }
}