package com.example.idea_book.presentation.ideas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idea_book.domain.use_cases.auth.GetTokenUseCase
import com.example.idea_book.domain.use_cases.auth.GetUserUseCase
import com.example.idea_book.domain.use_cases.ideas.GetIdeasUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdeasViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getIdeasUseCase: GetIdeasUseCase,
    private val getUserUseCase: GetUserUseCase,
): ViewModel() {
    private var _state by mutableStateOf(IdeasScreenState())
    private var _user by mutableStateOf(getUserUseCase())
    val user: FirebaseUser?
        get() = _user

    val state: IdeasScreenState
        get() = _state

    init {
        viewModelScope.launch {
            val token = getTokenUseCase()
            if (token != null) {
                val ideas = getIdeasUseCase(token)
                _state = _state.copy(ideas = ideas, isLoading = false)
            }
        }
    }
}
