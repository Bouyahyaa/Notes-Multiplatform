package com.bouyahya.notes.features.auth.ui.login

sealed interface LoginEvent {
    data class UpdateLoginFields(val loginForm: LoginForm) : LoginEvent
    data object Submit : LoginEvent
}