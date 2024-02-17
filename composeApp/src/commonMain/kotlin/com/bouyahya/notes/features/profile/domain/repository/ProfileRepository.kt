package com.bouyahya.notes.features.profile.domain.repository

import com.bouyahya.notes.features.profile.domain.model.Picture

interface ProfileRepository {
    suspend fun getRandomPicture(): Result<Picture>
}