package com.bouyahya.notes.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager {
    return remember {
        CameraManager(
            onLaunch = {
                println("CameraManager is not implemented")
            }
        )
    }
}

actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}