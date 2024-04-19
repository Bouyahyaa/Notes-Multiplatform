package com.bouyahya.notes.features.auth.data.remote

class AuthRemoteDataSource(
    private val authApi: AuthApi
) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return authApi.login(loginRequest = loginRequest)
    }
}