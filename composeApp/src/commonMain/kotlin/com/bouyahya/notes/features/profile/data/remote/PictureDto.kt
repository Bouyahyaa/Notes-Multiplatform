package com.bouyahya.notes.features.profile.data.remote

import com.bouyahya.notes.features.profile.domain.model.Picture
import kotlinx.serialization.Serializable

@Serializable
data class PictureDto(
    val id: String,
    val description: String?,
    val urls: Urls,
    val user: User
) {
    fun toModel() =
        Picture(
            id = id,
            description = description ?: "No description!",
            username = user.username,
            name = user.name,
            url = urls.regular
        )
}
