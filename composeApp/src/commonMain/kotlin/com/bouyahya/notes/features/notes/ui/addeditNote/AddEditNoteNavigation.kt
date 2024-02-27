package com.bouyahya.notes.features.notes.ui.addeditNote

sealed interface AddEditNoteNavigation {
    data object BackClick : AddEditNoteNavigation
}