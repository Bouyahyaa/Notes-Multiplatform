package com.bouyahya.notes.features.auth.ui.validation

import com.bouyahya.notes.core.validation.ValidationResult

object ValidateEmail {
    fun execute(value: String): ValidationResult {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        if (!value.matches(emailRegex)) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email address."
            )
        }
        return ValidationResult(successful = true)
    }
}