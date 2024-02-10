package com.bouyahya.notes.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.bouyahya.notes.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual fun Scope.createDriver(): SqlDriver {
    return AndroidSqliteDriver(AppDatabase.Schema, androidContext(), "AppDatabase.db")
}