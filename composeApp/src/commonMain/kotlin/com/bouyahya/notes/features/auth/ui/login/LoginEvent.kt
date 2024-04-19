package com.bouyahya.notes.features.auth.ui.login

sealed interface LoginEvent {
    data class UpdateLoginFields(val loginState: LoginState) : LoginEvent
    data object Submit : LoginEvent
}