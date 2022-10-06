package com.example.idea_book.domain.use_cases

import com.example.idea_book.core.validators.EmailValidator
import com.example.idea_book.core.validators.PasswordValidator
import com.example.idea_book.core.validators.UsernameValidator
import com.example.idea_book.domain.model.AuthActionResult
import com.example.idea_book.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val usernameValidator: UsernameValidator,
    private val passwordValidator: PasswordValidator,
    private val emailValidator: EmailValidator
){
    suspend fun execute(username: String, email: String, password: String): AuthActionResult {
        val emailErr = emailValidator(email)
        val usernameErr = usernameValidator(username)
        val passwordErr = passwordValidator(password)

        if (emailErr != null || usernameErr != null || passwordErr != null) {
            return AuthActionResult.Error(
                emailErr = emailErr,
                usernameErr = usernameErr,
                passwordErr = passwordErr
            )
        }

        return try {
            val success = authRepository.signUp(username, email, password)
            if (success) {
                AuthActionResult.Success()
            } else {
                AuthActionResult.Error(
                    firebaseErr = "Sign up failed"
                )
            }
        } catch(e: java.lang.Exception) {
            AuthActionResult.Error(
                firebaseErr = e.message ?: "Unknown error"
            )
        }
    }
}