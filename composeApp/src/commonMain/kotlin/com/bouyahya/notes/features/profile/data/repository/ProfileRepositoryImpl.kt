package com.bouyahya.notes.features.profile.data.repository

import com.bouyahya.notes.features.profile.data.remote.UnsplashClient
import com.bouyahya.notes.features.profile.domain.model.Picture
import com.bouyahya.notes.features.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val unsplashClient: UnsplashClient
) : ProfileRepository {
    override suspend fun getRandomPicture(): Result<Picture> =
        runCatching {
            unsplashClient.getRandomPicture().toModel()
        }
}