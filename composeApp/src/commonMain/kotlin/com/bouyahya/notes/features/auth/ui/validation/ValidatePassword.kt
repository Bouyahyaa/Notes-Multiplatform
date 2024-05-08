package com.bouyahya.notes.features.auth.ui.validation

import com.bouyahya.notes.core.validation.ValidationResult

object ValidatePassword {
    fun execute(value: String): ValidationResult {
        if (value.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "password must be at least 8 characters long"
            )
        }

        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$")
        if (!value.matches(passwordRegex)) {
            return ValidationResult(
                successful = false,
                errorMessage = "password must contain at least one uppercase letter, one lowercase letter, one number and one special character"
            )
        }

        return ValidationResult(successful = true)
    }
}