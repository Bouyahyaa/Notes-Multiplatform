package com.bouyahya.notes.core.di

import com.bouyahya.notes.core.database.databaseModule
import com.bouyahya.notes.core.network.networkModule
import com.bouyahya.notes.features.auth.di.authModule
import com.bouyahya.notes.features.notes.di.noteModule
import com.bouyahya.notes.features.profile.di.profileModule
import com.bouyahya.notes.features.videos.di.videoModule
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.PayloadData
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule(enableNetworkLogs),
            databaseModule,
            profileModule,
            videoModule,
            noteModule,
            authModule
        )
        onApplicationStart()
    }

private fun KoinApplication.onApplicationStart() {
    NotifierManager.addListener(object : NotifierManager.Listener {
        override fun onPushNotification(title: String?, body: String?) {
            super.onPushNotification(title, body)
            println("onPushNotificationStart: $title $body")
        }

        override fun onPayloadData(data: PayloadData) {
            super.onPayloadData(data)
            println("PayloadDataOnApplicationStart: $data")
        }
    })
}