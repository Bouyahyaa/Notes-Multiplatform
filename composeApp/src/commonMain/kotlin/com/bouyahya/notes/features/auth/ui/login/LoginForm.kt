package com.bouyahya.notes.features.auth.ui.login

import com.bouyahya.notes.core.validation.Field
import com.bouyahya.notes.features.auth.domain.usecase.ValidateEmail
import com.bouyahya.notes.features.auth.domain.usecase.ValidatePassword

data class LoginForm(
    val email: Field<String> = Field(
        value = "",
        validators = listOf(ValidateEmail)
    ),
    val password: Field<String> = Field(
        value = "",
        validators = listOf(ValidatePassword)
    ),
) {
    val isValid: Boolean
        get() = email.error == null &&
                password.error == null

}
