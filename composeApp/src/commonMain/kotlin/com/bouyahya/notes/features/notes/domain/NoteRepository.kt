package com.bouyahya.notes.features.notes.domain

interface NoteRepository {
    suspend fun getAllNotes(): Result<List<Note>>
    suspend fun getNoteById(id: String): Result<Note>
    suspend fun insertNote(note: Note): Result<Unit>
    suspend fun deleteNote(id: String): Result<Unit>
}