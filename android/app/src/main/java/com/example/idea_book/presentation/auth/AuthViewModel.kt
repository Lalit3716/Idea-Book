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

    fun signIn(email: String, password: String) {
        launchInViewModelScope { authRepository.signIn(email, password) }
    }

    fun signUp(username: String, email: String, password: String) {
        launchInViewModelScope { authRepository.singUp(username, email, password) }
    }

    fun signOut() {
        launchInViewModelScope { authRepository.signOut() }
    }

    private fun launchInViewModelScope(block: suspend () -> Boolean) {
        _authState = AuthState(isLoading = true)
        viewModelScope.launch {
            val success = block()
            _authState = if (success) {
                AuthState(isAuth = true)
            } else {
                AuthState(error = "Something went wrong!")
            }
        }
    }
}
