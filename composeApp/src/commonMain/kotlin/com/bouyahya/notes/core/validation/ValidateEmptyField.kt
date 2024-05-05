package com.bouyahya.notes.core.validation

object ValidateEmptyField : Validator<String> {
    override fun execute(value: String): String? {
        if (value.isBlank()) {
            return "This field is required"
        }
        return null
    }
}