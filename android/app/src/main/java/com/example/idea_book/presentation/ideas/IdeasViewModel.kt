package com.example.idea_book.presentation.ideas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idea_book.domain.model.IdeaModel
import com.example.idea_book.domain.use_cases.auth.GetTokenUseCase
import com.example.idea_book.domain.use_cases.auth.GetUserUseCase
import com.example.idea_book.domain.use_cases.ideas.CreateIdeaUseCase
import com.example.idea_book.domain.use_cases.ideas.DeleteIdeaUseCase
import com.example.idea_book.domain.use_cases.ideas.GetIdeasUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdeasViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getIdeasUseCase: GetIdeasUseCase,
    private val deleteIdeaUseCase: DeleteIdeaUseCase,
    private val createIdeaUseCase: CreateIdeaUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
    private var _state by mutableStateOf(IdeasScreenState())
    private var _user by mutableStateOf(getUserUseCase())
    val user: FirebaseUser?
        get() = _user

    val state: IdeasScreenState
        get() = _state

    private val _events = MutableSharedFlow<UIEvent>()
    val events = _events.asSharedFlow()

    private var recentlyDeletedIdea: IdeaModel? = null

    private var getIdeasJob: Job? = null

    init {
        viewModelScope.launch {
            val token = getTokenUseCase()
            if (token != null) {
                getIdeas(token)
            }
        }
    }

    fun onEvent(event: IdeasScreenEvent) {
        when (event) {
            is IdeasScreenEvent.DeleteIdea -> {
                viewModelScope.launch {
                    val token = getTokenUseCase()
                    if (token != null) {
                        recentlyDeletedIdea = event.idea
                        deleteIdeaUseCase(event.idea.id, token)
                        getIdeas(token)
                        _events.emit(UIEvent.ShowSnackBar)
                    }
                }
            }

            is IdeasScreenEvent.RestoreIdea -> {
                viewModelScope.launch {
                    val token = getTokenUseCase()
                    if (token != null) {
                        recentlyDeletedIdea?.let { idea ->
                            createIdeaUseCase(
                                title = idea.title,
                                content = idea.description,
                                color = idea.color,
                                token = token
                            )
                            getIdeas(token)
                        }
                    }
                }
            }
        }
    }

    private fun getIdeas(token: String) {
        getIdeasJob?.cancel()
        getIdeasJob = viewModelScope.launch {
            val ideas = getIdeasUseCase(token)
            _state = _state.copy(ideas = ideas, isLoading = false)
        }
    }

    sealed class UIEvent {
        object ShowSnackBar : UIEvent()
    }
}
