package com.example.idea_book.presentation.auth

data class AuthState(
    val isLoginMode: Boolean = false,
    val isAuth: Boolean = false,
    val isLoading: Boolean = false,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val firebaseError: String? = null,
    val usernameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null
)

sealed class AuthFormEvent {
    data class SignIn(val email: String, val password: String) : AuthFormEvent()
    data class SignUp(val username: String, val email: String, val password: String) : AuthFormEvent()
    data class EmailChange(val email: String) : AuthFormEvent()
    data class PasswordChange(val password: String) : AuthFormEvent()
    data class UsernameChange(val username: String) : AuthFormEvent()
    object ToggleMode : AuthFormEvent()
    object ClearErrors: AuthFormEvent()
}
