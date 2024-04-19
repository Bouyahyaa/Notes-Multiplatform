package com.bouyahya.notes.features.auth.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
)
