package com.example.idea_book.presentation.ideas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idea_book.domain.use_cases.auth.GetTokenUseCase
import com.example.idea_book.domain.use_cases.auth.GetUserUseCase
import com.example.idea_book.domain.use_cases.auth.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdeasViewModel @Inject constructor(
    getUserUseCase: GetUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getTokenUseCase: GetTokenUseCase
): ViewModel() {
    var user by mutableStateOf(getUserUseCase())
    var token: String? by mutableStateOf(null)

    init {
        viewModelScope.launch {
            token = getTokenUseCase()
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
            user = null
        }
    }
}
