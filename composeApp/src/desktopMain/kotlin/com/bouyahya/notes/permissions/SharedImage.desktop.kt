package com.bouyahya.notes.permissions

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.mohamedrejeb.calf.core.PlatformContext
import com.mohamedrejeb.calf.io.KmpFile
import com.mohamedrejeb.calf.io.readByteArray
import org.jetbrains.skia.Image

actual class SharedImage(
    private val file: KmpFile,
    private val context: PlatformContext
) {
    actual suspend fun toByteArray(): ByteArray? {
        return file.readByteArray(context)
    }

    actual suspend fun toImageBitmap(): ImageBitmap? {
        val byteArray = toByteArray()
        return if (byteArray != null) {
            Image.makeFromEncoded(byteArray).toComposeImageBitmap()
        } else {
            null
        }
    }
}