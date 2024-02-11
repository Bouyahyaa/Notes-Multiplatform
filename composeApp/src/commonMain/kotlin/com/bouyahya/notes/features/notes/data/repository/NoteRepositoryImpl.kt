package com.bouyahya.notes.features.notes.data.repository

import com.bouyahya.notes.features.notes.data.local.NoteLocalDataSource
import com.bouyahya.notes.features.notes.data.mapper.toNote
import com.bouyahya.notes.features.notes.data.mapper.toNoteEntity
import com.bouyahya.notes.features.notes.domain.Note
import com.bouyahya.notes.features.notes.domain.NoteRepository

class NoteRepositoryImpl(
    private val noteLocalDataSource: NoteLocalDataSource
) : NoteRepository {
    override suspend fun getAllNotes(): Result<List<Note>> =
        runCatching {
            noteLocalDataSource
                .getAllNotes()
                .map { it.toNote() }
        }

    override suspend fun getNoteById(id: Long): Result<Note> =
        runCatching {
            noteLocalDataSource
                .getNoteById(id)
                .toNote()
        }

    override suspend fun insertNote(note: Note): Result<Unit> =
        runCatching {
            noteLocalDataSource
                .insertNote(note.toNoteEntity())
        }


    override suspend fun deleteNote(id: Long): Result<Unit> =
        runCatching {
            noteLocalDataSource
                .deleteNote(id)
        }
}