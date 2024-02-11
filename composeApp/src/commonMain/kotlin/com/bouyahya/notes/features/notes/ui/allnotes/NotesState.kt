package com.bouyahya.notes.features.notes.ui.allnotes

import com.bouyahya.notes.features.notes.domain.Note

data class NotesState(
    val noteList: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)
