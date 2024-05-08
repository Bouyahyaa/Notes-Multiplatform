package com.bouyahya.notes.features.auth.ui.validation

import com.bouyahya.notes.core.validation.ValidationResult

object ValidateConfirmPassword {
    fun execute(value: String, password: String): ValidationResult {
        val validator = ValidatePassword.execute(value)

        if (!validator.successful) {
            return validator
        }

        if (value != password) {
            return ValidationResult(
                successful = false,
                errorMessage = "Passwords do not match"
            )
        }

        return ValidationResult(successful = true)
    }
}