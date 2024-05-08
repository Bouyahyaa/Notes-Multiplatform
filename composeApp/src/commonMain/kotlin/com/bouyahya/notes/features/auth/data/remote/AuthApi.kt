package com.bouyahya.notes.features.auth.data.remote

import com.bouyahya.notes.features.auth.data.remote.login.LoginRequest
import com.bouyahya.notes.features.auth.data.remote.login.LoginResponse
import com.bouyahya.notes.features.auth.data.remote.register.RegisterRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthApi(
    private val client: HttpClient
) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return client.post("users/signin") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()
    }

    suspend fun register(registerRequest: RegisterRequest) {
        client.post("users/signup") {
            contentType(ContentType.Application.Json)
            setBody(registerRequest)
        }
    }
}