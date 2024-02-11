package com.bouyahya.notes.features.notes.data.local

import com.bouyahya.notes.database.NoteEntity

interface NoteLocalDataSource {
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun getNoteById(id: Long): NoteEntity
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNote(id: Long)
}