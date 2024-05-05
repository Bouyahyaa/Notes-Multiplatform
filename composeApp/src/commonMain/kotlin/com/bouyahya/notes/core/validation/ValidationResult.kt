package com.bouyahya.notes.core.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)