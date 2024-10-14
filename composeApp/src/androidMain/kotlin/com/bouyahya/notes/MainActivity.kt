package com.bouyahya.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bouyahya.notes.core.di.initKoin
import com.mmk.kmpnotifier.permission.permissionUtil
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {
    private val permissionUtil by permissionUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin {
            androidContext(applicationContext)
            androidLogger()
        }

        setContent {
            App()
        }

        permissionUtil.askNotificationPermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}