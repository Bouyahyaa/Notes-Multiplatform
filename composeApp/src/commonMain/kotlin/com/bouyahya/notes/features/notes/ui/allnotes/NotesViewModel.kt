package com.bouyahya.notes.features.notes.ui.allnotes

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.bouyahya.notes.features.notes.domain.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteRepository,
    val state: MutableStateFlow<NotesState> = MutableStateFlow(NotesState())
) : ScreenModel {
    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.GetAllNotes -> getNotes()
            is NotesEvent.DeleteNote -> deleteNote(event)
        }
    }

    private fun getNotes() {
        screenModelScope.launch {
            state.update {
                it.copy(
                    isLoading = true
                )
            }
            noteRepository
                .getAllNotes()
                .onSuccess { notes ->
                    state.update {
                        it.copy(
                            noteList = notes
                        )
                    }
                }.onFailure {
                    state.update {
                        it.copy(
                            error = "Something went wrong!"
                        )
                    }
                }
        }.invokeOnCompletion {
            state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun deleteNote(event: NotesEvent.DeleteNote) {
        screenModelScope.launch {
            noteRepository
                .deleteNote(event.noteId)
                .onSuccess {
                    state.update {
                        it.copy(
                            noteList = state.value.noteList.filter { note ->
                                note.id != event.noteId
                            }
                        )
                    }
                }
        }
    }
}