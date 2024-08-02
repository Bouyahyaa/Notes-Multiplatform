package com.bouyahya.notes.features.auth.data.remote

import com.bouyahya.notes.features.auth.data.remote.login.LoginRequest
import com.bouyahya.notes.features.auth.data.remote.login.LoginResponse
import com.bouyahya.notes.features.auth.data.remote.register.RegisterRequest

class AuthRemoteDataSourceImpl(
    private val authApi: AuthApi
) : AuthRemoteDataSource {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return authApi.login(loginRequest = loginRequest)
    }

    override suspend fun register(registerRequest: RegisterRequest) {
        return authApi.register(registerRequest = registerRequest)
    }
}