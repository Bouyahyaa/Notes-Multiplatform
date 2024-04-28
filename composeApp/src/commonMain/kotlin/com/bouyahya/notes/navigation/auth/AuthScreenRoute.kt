package com.bouyahya.notes.navigation.auth

sealed class AuthScreenRoute(val route: String) {
    data object LoginScreen : AuthScreenRoute("login_screen")
}