package com.bouyahya.notes.features.notes.ui.addeditNote

data class AddEditNoteState(
    val addEditNoteForm: AddEditNoteForm = AddEditNoteForm(),
    val isLoading: Boolean = false,
)