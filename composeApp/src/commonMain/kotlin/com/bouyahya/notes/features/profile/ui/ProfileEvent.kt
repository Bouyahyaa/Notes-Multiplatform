package com.bouyahya.notes.features.profile.ui

import androidx.compose.ui.graphics.ImageBitmap

sealed interface ProfileEvent {
    data object GetProfile : ProfileEvent
    data class SetImage(val imageBitmap: ImageBitmap) : ProfileEvent
    data object Logout : ProfileEvent
}