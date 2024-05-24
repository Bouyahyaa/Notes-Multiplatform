package com.bouyahya.notes.permissions

import androidx.compose.runtime.Composable

@Composable
actual fun createPermissionsManager(callback: PermissionCallback): PermissionsManager {
    return PermissionsManager(callback)
}

actual class PermissionsManager actual constructor(private val callback: PermissionCallback) :
    PermissionHandler {
    @Composable
    override fun askPermission(permission: PermissionType) {
        throw NotImplementedError("RememberCameraManager is not implemented")
    }

    @Composable
    override fun isPermissionGranted(permission: PermissionType): Boolean {
        throw NotImplementedError("RememberCameraManager is not implemented")

    }

    @Composable
    override fun launchSettings() {
        throw NotImplementedError("RememberCameraManager is not implemented")
    }
}