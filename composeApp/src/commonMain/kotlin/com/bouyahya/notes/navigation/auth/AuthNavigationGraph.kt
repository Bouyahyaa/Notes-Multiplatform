package com.bouyahya.notes.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bouyahya.notes.features.auth.ui.login.LoginScreen
import com.bouyahya.notes.features.auth.ui.splash.SplashScreen
import com.bouyahya.notes.navigation.Graph

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreenRoute.SplashScreen.route
    ) {
        composable(route = AuthScreenRoute.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = AuthScreenRoute.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
    }
}