package com.bouyahya.notes.features.auth.data.remote

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
}