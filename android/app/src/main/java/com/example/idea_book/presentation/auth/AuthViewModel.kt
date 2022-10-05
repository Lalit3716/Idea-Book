package com.example.idea_book.presentation.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idea_book.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private var _authState by mutableStateOf(AuthState())
    val authState: AuthState get() = _authState

    init {
        _authState = AuthState(isLoading = true)
        viewModelScope.launch {
            authRepository.onAuthChangeListener { user ->
                _authState = if (user != null) {
                    AuthState(isAuth = true)
                } else {
                    AuthState(isAuth = false)
                }
            }
        }
    }

    private fun signIn(email: String, password: String) {
        launchInViewModelScope { authRepository.signIn(email, password) }
    }

    private fun signUp(username: String, email: String, password: String) {
        launchInViewModelScope { authRepository.singUp(username, email, password) }
    }

    fun signOut() {
        launchInViewModelScope { authRepository.signOut() }
    }

    private fun toggleAuthMode() {
        _authState = authState.copy(isLoginMode = !authState.isLoginMode)
        clearErrors()
    }

    private fun onChange(field: String, value: String) {
        when(field) {
            "email" -> _authState = authState.copy(email = value)
            "password" -> _authState = authState.copy(password = value)
            "username" -> _authState = authState.copy(username = value)
        }
    }

    private fun clearErrors() {
        _authState = authState.copy(
            emailError = null,
            passwordError = null,
            usernameError = null,
            firebaseError = null
        )
    }

    private fun launchInViewModelScope(authAction: suspend () -> Boolean) {
        _authState = AuthState(isLoading = true)
        viewModelScope.launch {
            val success = authAction()
            _authState = if (success) {
                AuthState(isAuth = true)
            } else {
                AuthState(firebaseError = "Something went wrong!")
            }
        }
    }

    fun onEvent(event: AuthFormEvent) {
        when (event) {
            is AuthFormEvent.SignIn -> signIn(event.email, event.password)
            is AuthFormEvent.SignUp -> signUp(event.username, event.email, event.password)
            is AuthFormEvent.ToggleMode -> toggleAuthMode()
            is AuthFormEvent.EmailChange -> onChange("email", event.email)
            is AuthFormEvent.PasswordChange -> onChange("password", event.password)
            is AuthFormEvent.UsernameChange -> onChange("username", event.username)
            else -> {
                Log.e("AuthViewModel", "Unknown event: $event")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

    }
}
