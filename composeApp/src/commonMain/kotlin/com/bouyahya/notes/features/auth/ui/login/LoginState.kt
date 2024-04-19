package com.bouyahya.notes.features.auth.ui.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
)