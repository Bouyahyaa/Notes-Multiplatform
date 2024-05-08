package com.bouyahya.notes.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bouyahya.notes.features.auth.ui.login.LoginScreen
import com.bouyahya.notes.features.auth.ui.register.RegisterScreen
import com.bouyahya.notes.navigation.Graph

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreenRoute.LoginScreen.route
    ) {
        composable(route = AuthScreenRoute.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(route = AuthScreenRoute.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
    }
}