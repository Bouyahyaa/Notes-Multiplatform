package com.bouyahya.notes.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bouyahya.notes.AppDatabase
import org.koin.core.scope.Scope

actual fun Scope.createDriver(): SqlDriver {
    return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        .also {
            AppDatabase.Schema.create(it)
        }
}
