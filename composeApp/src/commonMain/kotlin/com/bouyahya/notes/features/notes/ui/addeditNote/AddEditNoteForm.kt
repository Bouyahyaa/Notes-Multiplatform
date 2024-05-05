package com.bouyahya.notes.features.notes.ui.addeditNote

import com.bouyahya.notes.core.validation.Field
import com.bouyahya.notes.core.validation.ValidateEmptyField
import com.bouyahya.notes.features.notes.domain.model.Note

data class AddEditNoteForm(
    val title: Field<String> = Field(
        value = "",
        validators = listOf(ValidateEmptyField)
    ),
    val description: Field<String> = Field(
        value = "",
        validators = listOf(ValidateEmptyField)
    ),
) {
    val isValid: Boolean
        get() = title.error == null &&
                description.error == null

    val note: Note
        get() = Note.default.copy(
            title = title.value,
            description = description.value
        )
}
