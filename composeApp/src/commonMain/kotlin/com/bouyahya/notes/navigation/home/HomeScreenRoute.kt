package com.bouyahya.notes.navigation.home

sealed class HomeScreenRoute(val route: String) {
    data object Profile : HomeScreenRoute("profile_screen")
}