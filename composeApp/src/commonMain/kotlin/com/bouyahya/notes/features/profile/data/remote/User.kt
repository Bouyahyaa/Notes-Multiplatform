package com.bouyahya.notes.features.profile.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val name: String,
)
