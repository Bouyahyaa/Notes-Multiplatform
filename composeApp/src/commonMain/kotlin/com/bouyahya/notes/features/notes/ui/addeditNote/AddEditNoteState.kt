package com.bouyahya.notes.features.notes.ui.addeditNote

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.bouyahya.notes.features.notes.domain.Note

data class AddEditNoteState(
    val note: MutableState<Note> = mutableStateOf(Note.default),
    val isLoading: Boolean = false,
)