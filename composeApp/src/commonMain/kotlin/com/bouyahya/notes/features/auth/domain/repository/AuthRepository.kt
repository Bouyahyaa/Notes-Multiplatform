package com.bouyahya.notes.features.auth.domain.repository

import com.bouyahya.notes.core.utils.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String
    ): Result<Unit>
}