package com.bouyahya.notes.permissions

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImage {
    suspend fun toByteArray(): ByteArray?
    suspend fun toImageBitmap(): ImageBitmap?
}