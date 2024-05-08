package com.bouyahya.notes.navigation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bouyahya.notes.features.profile.ui.ProfileScreen
import com.bouyahya.notes.navigation.Graph
import com.bouyahya.notes.navigation.home.note.NoteNavigationGraph

@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = Graph.NOTE
    ) {
        composable(Graph.NOTE) {
            NoteNavigationGraph()
        }

        composable(HomeScreenRoute.Profile.route) {
            ProfileScreen()
        }
    }
}