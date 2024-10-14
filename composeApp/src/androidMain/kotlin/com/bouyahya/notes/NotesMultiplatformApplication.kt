package com.bouyahya.notes

import android.app.Application
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration

class NotesMultiplatformApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        NotifierManager.initialize(
            NotificationPlatformConfiguration.Android(
                notificationIconResId = R.drawable.ic_launcher_foreground,
                notificationChannelData = NotificationPlatformConfiguration.Android.NotificationChannelData(
                    id = "CHANNEL_ID_GENERAL",
                    name = "General"
                )
            )
        )
    }
}