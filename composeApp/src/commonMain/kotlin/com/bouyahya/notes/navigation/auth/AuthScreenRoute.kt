package com.bouyahya.notes.navigation.auth

sealed class AuthScreenRoute(val route: String) {
    data object SplashScreen : AuthScreenRoute("splash_screen")
    data object LoginScreen : AuthScreenRoute("login_screen")
}