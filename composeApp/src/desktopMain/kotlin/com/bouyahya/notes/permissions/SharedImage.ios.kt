package com.bouyahya.notes.permissions

import androidx.compose.ui.graphics.ImageBitmap

actual class SharedImage() {
    actual fun toByteArray(): ByteArray? {
        throw NotImplementedError("RememberCameraManager is not implemented")
    }

    actual fun toImageBitmap(): ImageBitmap? {
        throw NotImplementedError("RememberCameraManager is not implemented")
    }

    private companion object {
        const val COMPRESSION_QUALITY = 0.99
    }
}