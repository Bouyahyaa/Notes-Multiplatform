package com.bouyahya.notes.features.notes.ui.addeditNote

import com.bouyahya.notes.features.notes.domain.model.Note

data class AddEditNoteState(
    val note: Note = Note.default,
    val isLoading: Boolean = false,
)