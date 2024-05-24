package com.bouyahya.notes.permissions

import androidx.compose.runtime.Composable

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
    throw NotImplementedError("RememberCameraManager is not implemented")
}

actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}