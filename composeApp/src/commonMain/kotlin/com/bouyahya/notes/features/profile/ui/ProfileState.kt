package com.bouyahya.notes.features.profile.ui

import androidx.compose.ui.graphics.ImageBitmap
import com.bouyahya.notes.features.profile.domain.model.Picture

data class ProfileState(
    val picture: Picture? = null,
    val imageBitmap: ImageBitmap? = null,
    val isLoading: Boolean = true,
    val error: String = "",
)
