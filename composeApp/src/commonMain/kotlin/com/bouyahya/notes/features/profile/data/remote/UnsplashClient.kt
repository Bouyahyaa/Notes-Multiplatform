package com.bouyahya.notes.features.profile.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class UnsplashClient(
    private val httpClient: HttpClient
) {
    suspend fun getRandomPicture(): PictureDto {
        return httpClient.get("${BASE_URL}photos/random/?client_id=${APP_ID}") {
            contentType(ContentType.Application.Json)
        }.body<PictureDto>()
    }

    companion object {
        const val APP_ID: String = "8506DXzJzHjxWBSAzneHIS3u8SnkdHhNdNBbDal7ePU"
        const val BASE_URL: String = "https://api.unsplash.com/"
    }
}