package com.bouyahya.notes.features.notes.ui.allnotes

sealed interface NotesEvent {
    data object GetAllNotes : NotesEvent
    data class DeleteNote(val noteId: Long) : NotesEvent
}