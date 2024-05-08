package com.bouyahya.notes.navigation.auth

sealed class AuthScreenRoute(val route: String) {
    data object LoginScreen : AuthScreenRoute("login_screen")
    data object RegisterScreen : AuthScreenRoute("register_screen")
}