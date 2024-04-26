package com.bouyahya.notes.navigation.home.note

sealed class NoteScreenRoute(val route: String) {
    data object AllNotes : NoteScreenRoute("all_notes_screen")
    data object AddEditNote : NoteScreenRoute("add_edit_note_screen")
}