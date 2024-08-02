package com.bouyahya.notes.core.network

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json

class MockClient(
    private val defaultResponse: MockResponse = MockResponse.default(),
) : ResponseInterceptor {

    private var _call: (String) -> MockResponse = { defaultResponse }

    fun setResponse(response: MockResponse) {
        _call = { response }
    }

    override fun invoke(request: HttpRequestData): MockResponse {
        return _call.invoke(request.url.fullPath)
    }
}

fun interface ResponseInterceptor {
    operator fun invoke(request: HttpRequestData): MockResponse
}

data class MockResponse(
    val content: String,
    val status: HttpStatusCode
) {
    companion object {
        fun ok(content: String) = MockResponse(content, HttpStatusCode.OK)
        fun default() = ok("{}")
    }
}

fun testKtorClient(mockClient: MockClient = MockClient()): HttpClient {
    val engine = testKtorEngine(mockClient)
    return HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}

private fun testKtorEngine(interceptor: ResponseInterceptor) = MockEngine { request ->
    val response = interceptor(request)
    respond(
        content = ByteReadChannel(response.content),
        status = response.status,
        headers = headersOf(HttpHeaders.ContentType, "application/json")
    )
}