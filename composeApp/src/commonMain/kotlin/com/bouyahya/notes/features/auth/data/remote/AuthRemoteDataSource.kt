package com.bouyahya.notes.features.auth.data.remote

import com.bouyahya.notes.features.auth.data.remote.login.LoginRequest
import com.bouyahya.notes.features.auth.data.remote.login.LoginResponse
import com.bouyahya.notes.features.auth.data.remote.register.RegisterRequest

interface AuthRemoteDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun register(registerRequest: RegisterRequest)
}