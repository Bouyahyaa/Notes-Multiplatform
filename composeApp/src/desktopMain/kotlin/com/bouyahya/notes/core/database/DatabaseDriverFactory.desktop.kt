package com.bouyahya.notes.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bouyahya.notes.AppDatabase
import org.koin.core.scope.Scope

actual fun Scope.createDriver(): SqlDriver {
    val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    AppDatabase.Schema.create(driver)
    return driver
}