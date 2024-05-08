package com.bouyahya.notes.features.notes.data.mapper

import com.bouyahya.notes.database.NoteEntity
import com.bouyahya.notes.features.notes.domain.model.Note

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title ?: "",
        description = description ?: ""
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        description = description
    )
}