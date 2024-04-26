package com.bouyahya.notes.navigation.home.note

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bouyahya.notes.features.notes.ui.addeditNote.AddEditNoteScreen
import com.bouyahya.notes.features.notes.ui.allnotes.NotesScreen

@Composable
fun NoteNavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NoteScreenRoute.AllNotes.route
    ) {
        composable(NoteScreenRoute.AllNotes.route) {
            NotesScreen(navController = navController)
        }

        composable(
            NoteScreenRoute.AddEditNote.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1
                },
            )
        ) {
            AddEditNoteScreen(
                navController = navController,
                noteId = it.arguments?.getLong("id") ?: -1
            )
        }
    }
}