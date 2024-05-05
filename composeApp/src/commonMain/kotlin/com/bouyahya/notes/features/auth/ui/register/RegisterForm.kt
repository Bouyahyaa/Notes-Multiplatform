package com.bouyahya.notes.features.auth.ui.register

data class RegisterForm(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val phone: String = ""
)
