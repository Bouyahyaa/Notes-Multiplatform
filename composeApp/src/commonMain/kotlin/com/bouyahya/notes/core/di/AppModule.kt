package com.bouyahya.notes.core.di

import com.bouyahya.notes.core.database.databaseModule

val appModule
    get() = listOf(
        databaseModule
    )