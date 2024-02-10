package com.bouyahya.notes.core.database

import org.koin.dsl.module

val databaseModule
    get() = module {
        factory { createDriver() }
    }