package com.bouyahya.notes.features.auth.ui.register

data class RegisterState(
    val registerForm: RegisterForm = RegisterForm(),
    val isLoading: Boolean = false,
)