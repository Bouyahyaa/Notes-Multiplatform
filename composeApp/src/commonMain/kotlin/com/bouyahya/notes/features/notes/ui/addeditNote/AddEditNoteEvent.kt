package com.bouyahya.notes.features.notes.ui.addeditNote

import com.bouyahya.notes.features.notes.domain.model.Note

sealed interface AddEditNoteEvent {
    data class UpdateNoteFields(val note: Note) : AddEditNoteEvent
    data object Submit : AddEditNoteEvent
}