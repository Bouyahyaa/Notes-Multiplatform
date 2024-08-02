package com.bouyahya.notes.core.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

expect fun Scope.createDriver(): SqlDriver