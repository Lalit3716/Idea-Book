package com.example.idea_book.presentation.ideas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idea_book.domain.repository.AuthRepository
import com.example.idea_book.domain.use_cases.AuthListenerUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdeasViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authListenerUseCase: AuthListenerUseCase
): ViewModel() {
    var isAuth by mutableStateOf(true)

    init {
        viewModelScope.launch {
            authListenerUseCase.addListener("IdeasViewModel") {
                if (it == null) {
                    isAuth = false
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

    fun getUser(): FirebaseUser? {
        return authRepository.getUser()
    }

    override fun onCleared() {
        super.onCleared()
        authListenerUseCase.removeListener("IdeasViewModel")
    }
}
