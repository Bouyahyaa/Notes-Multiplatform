package com.bouyahya.notes.features.notes.ui.allnotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bouyahya.notes.features.notes.ui.allnotes.components.NotesListScreen
import org.koin.compose.koinInject

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(NotesEvent.GetAllNotes)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error.isNotEmpty()) {
            Text(text = state.error)
        } else {
            NotesListScreen(
                notesList = state.noteList,
                onEvent = viewModel::onEvent,
                navController = navController
            )
        }
    }
}