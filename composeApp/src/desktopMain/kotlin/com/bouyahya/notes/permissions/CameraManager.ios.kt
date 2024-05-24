package com.bouyahya.notes.permissions

import androidx.compose.runtime.Composable

@Composable
actual fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager {
    throw NotImplementedError("RememberCameraManager is not implemented")
}

actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}