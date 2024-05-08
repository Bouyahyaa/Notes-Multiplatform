package com.bouyahya.notes.core.di

import com.bouyahya.notes.core.database.databaseModule
import com.bouyahya.notes.core.network.networkModule
import com.bouyahya.notes.features.auth.di.authModule
import com.bouyahya.notes.features.notes.di.noteModule
import com.bouyahya.notes.features.profile.di.profileModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule(enableNetworkLogs),
            databaseModule,
            profileModule,
            noteModule,
            authModule
        )
    }