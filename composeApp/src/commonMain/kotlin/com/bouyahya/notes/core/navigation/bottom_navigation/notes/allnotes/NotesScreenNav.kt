package com.bouyahya.notes.core.navigation.bottom_navigation.notes.allnotes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bouyahya.notes.core.navigation.bottom_navigation.notes.addeditnote.AddEditNoteScreenNav
import com.bouyahya.notes.features.notes.ui.addeditNote.AddEditNoteScreen
import com.bouyahya.notes.features.notes.ui.allnotes.AllNotesNavigation
import com.bouyahya.notes.features.notes.ui.allnotes.NotesEvent
import com.bouyahya.notes.features.notes.ui.allnotes.NotesScreen
import com.bouyahya.notes.features.notes.ui.allnotes.NotesViewModel

object NotesScreenNav : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = getScreenModel<NotesViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.onEvent(NotesEvent.GetAllNotes)
        }

        NotesScreen(
            state = state,
            onEvent = { viewModel.onEvent(it) },
            onNavigation = {
                when (it) {
                    is AllNotesNavigation.NavigateToAddEditNote -> {
                        navigator.push(AddEditNoteScreenNav(id = it.noteId))
                    }
                }
            }
        )
    }
}