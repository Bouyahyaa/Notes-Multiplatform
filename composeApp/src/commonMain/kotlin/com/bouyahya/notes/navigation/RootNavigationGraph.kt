package com.bouyahya.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bouyahya.notes.navigation.auth.authNavGraph
import com.bouyahya.notes.navigation.home.HomeScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController = navController)

        composable(route = Graph.HOME) { HomeScreen() }
    }
}