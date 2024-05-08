package com.bouyahya.notes.core.network

import com.bouyahya.notes.core.error.ServerError
import com.bouyahya.notes.core.error.ServerException
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

const val BASE_URL = "http://<IP_ADDRESS>/"
internal fun createHttpClient(enableLogging: Boolean): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        defaultRequest { url(BASE_URL) }

        HttpResponseValidator {
            validateResponse { response ->
                if (!response.status.isSuccess()) {
                    val serverError = Json.decodeFromString<ServerError>(response.bodyAsText())
                    throw ServerException(serverError.message)
                }
            }
        }
    }
}