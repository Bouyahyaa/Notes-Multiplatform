package com.bouyahya.notes.features.auth.domain.usecase

import com.bouyahya.notes.core.validation.Validator

object ValidateEmail : Validator<String> {
    override fun execute(value: String): String? {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        if (!value.matches(emailRegex)) {
            return "That's not a valid email address."
        }
        return null
    }
}