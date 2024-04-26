package com.bouyahya.notes.features.auth.ui.splash

import androidx.lifecycle.ViewModel
import com.russhwolf.settings.Settings

class SplashViewModel(
    private val settings: Settings
) : ViewModel() {
    fun authenticateCheck(): Boolean {
        val token = settings.getString("token", "")
        return token.isNotEmpty()
    }
}