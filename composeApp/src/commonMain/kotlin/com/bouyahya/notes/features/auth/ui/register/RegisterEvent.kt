package com.bouyahya.notes.features.auth.ui.register

sealed interface RegisterEvent {
    data class UpdateRegisterForm(val registerForm: RegisterForm) : RegisterEvent
    data object Submit : RegisterEvent
}