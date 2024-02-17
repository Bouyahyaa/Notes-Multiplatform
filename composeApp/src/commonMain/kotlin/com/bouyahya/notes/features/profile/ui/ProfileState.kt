package com.bouyahya.notes.features.profile.ui

import com.bouyahya.notes.features.profile.domain.model.Picture

data class ProfileState(
    val picture: Picture? = null,
    val isLoading: Boolean = false,
    val error: String = "",
)
