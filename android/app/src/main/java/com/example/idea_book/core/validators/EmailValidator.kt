package com.example.idea_book.core.validators

import android.util.Patterns.EMAIL_ADDRESS

class EmailValidator {
    operator fun invoke(email: String): String? {
        return when {
            email.isEmpty() -> "Email cannot be empty"
            !EMAIL_ADDRESS.matcher(email).matches() -> "Email is invalid"
            else -> null
        }
    }
}
