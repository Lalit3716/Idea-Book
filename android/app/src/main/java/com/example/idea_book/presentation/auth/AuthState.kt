package com.example.idea_book.presentation.auth

data class AuthState(
    val isAuth: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
