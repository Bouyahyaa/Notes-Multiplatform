package com.bouyahya.notes.features.auth.data.remote.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val phone: String
)
