package com.bouyahya.notes.core.di

import com.bouyahya.notes.core.database.databaseModule
import com.bouyahya.notes.features.notes.di.noteModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            databaseModule,
            noteModule
        )
    }