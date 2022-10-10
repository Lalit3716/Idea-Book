package com.example.idea_book.presentation.create_idea

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idea_book.domain.model.IdeaModel
import com.example.idea_book.domain.use_cases.auth.GetTokenUseCase
import com.example.idea_book.domain.use_cases.ideas.CreateIdeaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateIdeaViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val createIdeasUseCase: CreateIdeaUseCase
) : ViewModel() {
    private val _ideaTitle = mutableStateOf(IdeaTextFieldState(
        hint = "Enter title..."
    ))
    val ideaTitle: State<IdeaTextFieldState> = _ideaTitle

    private val _ideaContent = mutableStateOf(IdeaTextFieldState(
        hint = "Enter content..."
    ))
    val ideaContent: State<IdeaTextFieldState> = _ideaContent

    private val _ideaColor = mutableStateOf(IdeaModel.ideaColors.random().toArgb())
    val ideaColor: State<Int> = _ideaColor

    private val _events = MutableSharedFlow<UIEvents>()
    val events = _events.asSharedFlow()

    fun onEvent(event: CreateIdeaEvent) {
        when(event) {
            is CreateIdeaEvent.EnteredTitle -> {
                _ideaTitle.value = _ideaTitle.value.copy(
                    text = event.value
                )
            }
            is CreateIdeaEvent.ChangeTitleFocus -> {
                _ideaTitle.value = _ideaTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            ideaTitle.value.text.isBlank()
                )
            }
            is CreateIdeaEvent.EnteredContent -> {
                _ideaContent.value = _ideaContent.value.copy(
                    text = event.value
                )
            }
            is CreateIdeaEvent.ChangeContentFocus -> {
                _ideaContent.value = _ideaContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _ideaContent.value.text.isBlank()
                )
            }
            is CreateIdeaEvent.ChangeColor -> {
                _ideaColor.value = event.color
            }
            is CreateIdeaEvent.SaveIdea -> {
                viewModelScope.launch {
                    val token = getTokenUseCase()
                    createIdeasUseCase(
                        title = ideaTitle.value.text,
                        content = ideaContent.value.text,
                        color = ideaColor.value,
                        token = token!!
                    )
                    _events.emit(UIEvents.ShowSnackBar("Idea created successfully!"))
                }
            }
        }
    }

    sealed class UIEvents {
        data class ShowSnackBar(val message: String): UIEvents()
    }
}
