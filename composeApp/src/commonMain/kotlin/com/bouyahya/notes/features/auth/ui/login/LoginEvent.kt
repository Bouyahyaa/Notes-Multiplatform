package com.bouyahya.notes.features.auth.ui.login

sealed interface LoginEvent {
    data class UpdateLoginForm(val loginForm: LoginForm) : LoginEvent
    data object Submit : LoginEvent
}