package com.bouyahya.notes.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.bouyahya.notes.AppDatabase
import org.koin.core.scope.Scope
import java.io.File

actual fun Scope.createDriver(): SqlDriver {
    val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:AppDatabase.db")
    if (!File("AppDatabase.db").exists())  // check if database alread created or not
    {
        AppDatabase.Schema.create(driver)
    }
    return driver
}