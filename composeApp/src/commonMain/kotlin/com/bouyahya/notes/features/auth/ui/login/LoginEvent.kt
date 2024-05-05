package com.bouyahya.notes.features.auth.ui.login

sealed interface LoginEvent {
    data class UpdateEmail(val loginForm: LoginForm) : LoginEvent
    data class UpdatePassword(val loginForm: LoginForm) : LoginEvent
    data object Submit : LoginEvent
}