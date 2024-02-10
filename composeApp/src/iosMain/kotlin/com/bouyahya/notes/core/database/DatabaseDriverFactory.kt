package com.bouyahya.notes.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.bouyahya.notes.AppDatabase
import org.koin.core.scope.Scope

actual fun Scope.createDriver(): SqlDriver {
    return NativeSqliteDriver(AppDatabase.Schema, "AppDatabase.db")
}