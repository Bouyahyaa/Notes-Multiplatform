package com.bouyahya.notes.features.notes.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.bouyahya.notes.features.notes.domain.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteRepository,
    val state: MutableStateFlow<NotesState> = MutableStateFlow(NotesState())
) : ScreenModel {
    init {
        screenModelScope.launch {
            noteRepository
                .getAllNotes()
                .onSuccess {
                    println("noteRepositoryOnSuccess $it")
                }.onFailure {
                    println("noteRepositoryOnFailure $it")
                }
        }
    }
}