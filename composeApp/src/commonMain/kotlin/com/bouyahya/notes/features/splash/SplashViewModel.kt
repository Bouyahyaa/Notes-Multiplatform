package com.bouyahya.notes.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmk.kmpnotifier.notification.NotifierManager
import com.russhwolf.settings.Settings
import kotlinx.coroutines.launch

class SplashViewModel(
    private val settings: Settings
) : ViewModel() {
    fun authenticateCheck(): Boolean {
        subscribeToTopic()
        val token = settings.getString("token", "")
        return token.isNotEmpty()
    }

    private fun subscribeToTopic() =
        viewModelScope.launch {
            val subscribed = settings.getBooleanOrNull(key = "subscribed")
            if (subscribed == null) {
                settings.putBoolean("subscribed", true)
                NotifierManager.getPushNotifier().subscribeToTopic("notes_multiplatform")
            }
        }
}