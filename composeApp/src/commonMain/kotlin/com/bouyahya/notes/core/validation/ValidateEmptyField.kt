package com.bouyahya.notes.core.validation

object ValidateEmptyField {
    fun execute(value: String): ValidationResult {
        if (value.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "This field is required."
            )
        }
        return ValidationResult(successful = true)
    }
}