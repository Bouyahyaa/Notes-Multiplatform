package com.bouyahya.notes.core.database

import com.russhwolf.settings.Settings
import org.koin.dsl.module

val databaseModule
    get() = module {
        single { Settings() }
        factory { createDriver() }
    }