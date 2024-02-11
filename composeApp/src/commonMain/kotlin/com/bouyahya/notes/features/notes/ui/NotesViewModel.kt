package com.bouyahya.notes.features.notes.ui

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
    init {
        screenModelScope.launch {
            state.update {
                it.copy(
                    isLoading = true
                )
            }
            noteRepository
                .getAllNotes()
                .onSuccess { notes ->
                    println("noteRepositoryOnSuccess $notes")
                    state.update {
                        it.copy(
                            noteList = notes
                        )
                    }
                }.onFailure {
                    println("noteRepositoryOnFailure $it")
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
}