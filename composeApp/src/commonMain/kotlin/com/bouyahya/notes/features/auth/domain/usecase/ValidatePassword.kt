package com.bouyahya.notes.features.auth.domain.usecase

import com.bouyahya.notes.core.validation.Validator

object ValidatePassword : Validator<String> {
    override fun execute(value: String): String? {
        if (value.length < 8) {
            return "The password needs to consist at least 8 characters"
        }

        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$")
        if (!value.matches(passwordRegex)) {
            return "password must contain at least one uppercase letter, one lowercase letter, one number and one special character"
        }

        return null
    }
}