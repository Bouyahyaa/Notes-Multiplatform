package com.bouyahya.notes.features.notes.data.local

import com.bouyahya.notes.AppDatabase
import com.bouyahya.notes.database.NoteEntity

class NoteLocalDataSourceImpl(
    database: AppDatabase
) : NoteLocalDataSource {
    private val queries = database.noteQueries
    override suspend fun getAllNotes(): List<NoteEntity> {
        return queries
            .getAllNotes()
            .executeAsList()
    }

    override suspend fun getNoteById(id: String): NoteEntity {
        return queries
            .getNote(id)
            .executeAsOne()
    }

    override suspend fun insertNote(note: NoteEntity) {
        queries.insertNote(note)
    }

    override suspend fun deleteNote(id: String) {
        queries.deleteNote(id)
    }
}