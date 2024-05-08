package com.bouyahya.notes.features.auth.ui.login

data class LoginState(
    val loginForm: LoginForm = LoginForm(),
    val isLoading: Boolean = false,
)