package com.example.idea_book.domain.use_cases

import com.example.idea_book.core.validators.EmailValidator
import com.example.idea_book.core.validators.PasswordValidator
import com.example.idea_book.domain.model.AuthActionResult
import com.example.idea_book.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) {
    suspend fun execute(email: String, password: String): AuthActionResult {
        val emailErr = emailValidator(email)
        val passwordErr = passwordValidator(password)

        if (emailErr != null || passwordErr != null) {
            return AuthActionResult.Error(emailErr = emailErr, passwordErr = passwordErr)
        }

        return try {
            val success = authRepository.signIn(email, password)
            if (success) {
                AuthActionResult.Success()
            } else {
                AuthActionResult.Error(firebaseErr = "Sign in failed")
            }
        } catch (e: Exception) {
            AuthActionResult.Error(firebaseErr = e.message ?: "Unknown error")
        }
    }
}
