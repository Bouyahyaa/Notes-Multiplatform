package com.bouyahya.notes.features.notes

import app.cash.sqldelight.db.SqlDriver
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import com.bouyahya.notes.AppDatabase
import com.bouyahya.notes.core.database.createDriver
import com.bouyahya.notes.database.NoteEntity
import com.bouyahya.notes.features.notes.data.local.NoteLocalDataSource
import com.bouyahya.notes.features.notes.data.local.NoteLocalDataSourceImpl
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test


class NoteLocalDataSourceTest : KoinTest {

    private val testNoteEntity = NoteEntity(
        id = 1L,
        title = "title",
        description = "description",
    )

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                module {
                    single<SqlDriver> { createDriver() }
                    single<NoteLocalDataSource> {
                        NoteLocalDataSourceImpl(
                            database = AppDatabase(driver = get())
                        )
                    }
                }
            )
        }
    }


    @Test
    fun `cache notes`() = runTest {
        //act
        val noteLocalDataSource = getKoin().get<NoteLocalDataSource>()
        noteLocalDataSource.insertNote(testNoteEntity)
        val result = noteLocalDataSource.getAllNotes()

        //verify
        assertThat(result).isNotEmpty()
    }

    @Test
    fun `cache single note`() = runTest {
        //act
        val noteLocalDataSource = getKoin().get<NoteLocalDataSource>()
        noteLocalDataSource.insertNote(testNoteEntity)
        val result = noteLocalDataSource.getNoteById(id = 1L)

        //verify
        assertThat(result).isEqualTo(testNoteEntity)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}