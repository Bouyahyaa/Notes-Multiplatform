package com.bouyahya.notes.features.notes.ui.allnotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bouyahya.notes.features.notes.ui.allnotes.components.NotesListScreen

@Composable
fun NotesScreen(
    state: NotesState,
    onEvent: (NotesEvent) -> Unit,
    onNavigation: (AllNotesNavigation) -> Unit
) {
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
                onEvent = onEvent,
                onNavigation = onNavigation
            )
        }
    }
}