package com.bouyahya.notes.features.auth.domain.repository

import com.bouyahya.notes.core.utils.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
}