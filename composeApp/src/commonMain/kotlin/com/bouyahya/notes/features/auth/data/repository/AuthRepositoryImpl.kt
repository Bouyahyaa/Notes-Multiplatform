package com.bouyahya.notes.features.auth.data.repository

import com.bouyahya.notes.core.utils.Repository
import com.bouyahya.notes.core.utils.Result
import com.bouyahya.notes.features.auth.data.remote.AuthRemoteDataSource
import com.bouyahya.notes.features.auth.data.remote.LoginRequest
import com.bouyahya.notes.features.auth.domain.repository.AuthRepository
import com.russhwolf.settings.Settings

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val settings: Settings
) : AuthRepository, Repository() {
    override suspend fun login(email: String, password: String): Result<Unit> =
        runCatching {
            val response = authRemoteDataSource.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
            settings.putString("token", response.token)
        }
}