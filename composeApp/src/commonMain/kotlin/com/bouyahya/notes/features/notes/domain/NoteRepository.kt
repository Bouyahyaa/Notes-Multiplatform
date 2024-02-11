package com.bouyahya.notes.features.notes.domain

interface NoteRepository {
    suspend fun getAllNotes(): Result<List<Note>>
    suspend fun getNoteById(id: Long): Result<Note>
    suspend fun insertNote(note: Note): Result<Unit>
    suspend fun deleteNote(id: Long): Result<Unit>
}