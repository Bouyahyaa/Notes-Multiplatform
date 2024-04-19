package com.bouyahya.notes.core.error

import kotlinx.serialization.Serializable

@Serializable
data class ServerError(
    val message: String
)