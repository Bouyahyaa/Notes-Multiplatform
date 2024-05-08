package com.bouyahya.notes.features.auth.data.remote.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
)
