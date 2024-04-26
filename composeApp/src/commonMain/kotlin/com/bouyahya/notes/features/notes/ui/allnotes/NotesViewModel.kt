package com.bouyahya.notes.features.notes.ui.allnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bouyahya.notes.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteRepository,
    val state: MutableStateFlow<NotesState> = MutableStateFlow(NotesState()),
) : ViewModel() {
    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.GetAllNotes -> getNotes()
            is NotesEvent.DeleteNote -> deleteNote(event)
        }
    }

    private fun getNotes() {
        viewModelScope.launch {
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
        viewModelScope.launch {
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