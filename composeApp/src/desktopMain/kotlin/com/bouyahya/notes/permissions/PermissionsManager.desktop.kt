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
        println("Desktop access without permission")
    }

    @Composable
    override fun isPermissionGranted(permission: PermissionType): Boolean {
        return true
    }

    @Composable
    override fun launchSettings() {
        println("launchSettings is not required for desktop")
    }
}