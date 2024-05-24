package com.bouyahya.notes.permissions.shared

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import com.bouyahya.notes.permissions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SharedManager(
    onChangeImageBitmap: (ImageBitmap?) -> Unit,
    onChangeImageSourceOptionDialog: (Boolean) -> Unit,
    imageSourceOptionDialog: Boolean,
) {
    val coroutineScope = rememberCoroutineScope()
    var launchCamera by remember { mutableStateOf(value = false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    var launchSetting by remember { mutableStateOf(value = false) }
    var permissionRationalDialog by remember { mutableStateOf(value = false) }
    val permissionsManager = createPermissionsManager(object : PermissionCallback {
        override fun onPermissionStatus(
            permissionType: PermissionType,
            status: PermissionStatus
        ) {
            when (status) {
                PermissionStatus.GRANTED -> {
                    when (permissionType) {
                        PermissionType.CAMERA -> launchCamera = true
                        PermissionType.GALLERY -> launchGallery = true
                    }
                }

                else -> {
                    permissionRationalDialog = true
                }
            }
        }
    })

    val cameraManager = rememberCameraManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                it?.toImageBitmap()
            }
            onChangeImageBitmap(bitmap)
        }
    }

    val galleryManager = rememberGalleryManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                it?.toImageBitmap()
            }
            onChangeImageBitmap(bitmap)
        }
    }
    if (imageSourceOptionDialog) {
        ImageSourceOptionDialog(
            onDismissRequest = {
                onChangeImageSourceOptionDialog(false)
            }, onGalleryRequest = {
                onChangeImageSourceOptionDialog(false)
                launchGallery = true
            }, onCameraRequest = {
                onChangeImageSourceOptionDialog(false)
                launchCamera = true
            })
    }
    if (launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.GALLERY)
        }
        launchGallery = false
    }
    if (launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.CAMERA)
        }
        launchCamera = false
    }
    if (launchSetting) {
        permissionsManager.launchSettings()
        launchSetting = false
    }
    if (permissionRationalDialog) {
        AlertMessageDialog(title = "Permission Required",
            message = "To set your profile picture, please grant this permission. You can manage permissions in your device settings.",
            positiveButtonText = "Settings",
            negativeButtonText = "Cancel",
            onPositiveClick = {
                permissionRationalDialog = false
                launchSetting = true

            },
            onNegativeClick = {
                permissionRationalDialog = false
            })
    }
}
