package com.bouyahya.notes.features.notes.di

import androidx.lifecycle.SavedStateHandle
import com.bouyahya.notes.AppDatabase
import com.bouyahya.notes.features.notes.data.local.NoteLocalDataSource
import com.bouyahya.notes.features.notes.data.local.NoteLocalDataSourceImpl
import com.bouyahya.notes.features.notes.data.repository.NoteRepositoryImpl
import com.bouyahya.notes.features.notes.domain.repository.NoteRepository
import com.bouyahya.notes.features.notes.ui.addeditNote.AddEditNoteViewModel
import com.bouyahya.notes.features.notes.ui.allnotes.NotesViewModel
import org.koin.dsl.module

val noteModule
    get() = module {
        single<NoteLocalDataSource> {
            NoteLocalDataSourceImpl(
                database = AppDatabase(driver = get())
            )
        }

        single<NoteRepository> {
            NoteRepositoryImpl(
                noteLocalDataSource = get()
            )
        }

        factory {
            NotesViewModel(
                noteRepository = get(),
            )
        }

        factory {
            AddEditNoteViewModel(
                noteRepository = get(),
            )
        }
    }