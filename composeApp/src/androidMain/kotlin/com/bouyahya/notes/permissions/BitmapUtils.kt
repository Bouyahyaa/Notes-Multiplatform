package com.bouyahya.notes.permissions

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream

object BitmapUtils {
    fun getBitmapFromUri(uri: Uri, contentResolver: ContentResolver): android.graphics.Bitmap? {
        var inputStream: InputStream? = null
        try {
            inputStream = contentResolver.openInputStream(uri)
            val s = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()
            return s
        } catch (e: Exception) {
            e.printStackTrace()
            println("getBitmapFromUri Exception: ${e.message}")
            println("getBitmapFromUri Exception: ${e.localizedMessage}")
            return null
        }
    }
}