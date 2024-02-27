package com.bouyahya.notes.features.notes.ui.allnotes

sealed interface AllNotesNavigation {
    data class NavigateToAddEditNote(val noteId: Long? = null) : AllNotesNavigation
}