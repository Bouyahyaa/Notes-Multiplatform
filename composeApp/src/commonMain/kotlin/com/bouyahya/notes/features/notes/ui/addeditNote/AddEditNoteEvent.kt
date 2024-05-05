package com.bouyahya.notes.features.notes.ui.addeditNote


sealed interface AddEditNoteEvent {
    data class GetNote(val noteId: Long) : AddEditNoteEvent
    data class UpdateTitle(val addEditNoteForm: AddEditNoteForm) : AddEditNoteEvent
    data class UpdateDescription(val addEditNoteForm: AddEditNoteForm) : AddEditNoteEvent
    data class Submit(val noteId: Long) : AddEditNoteEvent
}