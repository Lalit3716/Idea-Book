package com.example.idea_book.presentation.create_idea

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idea_book.core.utils.ActionResult
import com.example.idea_book.domain.model.TagModel
import com.example.idea_book.domain.use_cases.auth.GetTokenUseCase
import com.example.idea_book.domain.use_cases.ideas.CreateIdeaUseCase
import com.example.idea_book.domain.use_cases.ideas.GetTagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateIdeaViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val createIdeasUseCase: CreateIdeaUseCase,
    private val getAllTagsUseCase: GetTagsUseCase
) : ViewModel() {
    private val _tags = mutableStateListOf<TagModel>()
    val tags: List<TagModel> get() = _tags

    private val _selectedTags = mutableStateListOf<TagModel>()
    val selectedTags: List<TagModel> get() = _selectedTags

    private val _ideaTitle = mutableStateOf(
        IdeaTextFieldState(
            hint = "Enter title..."
        )
    )
    val ideaTitle: State<IdeaTextFieldState> = _ideaTitle

    private val _ideaContent = mutableStateOf(
        IdeaTextFieldState(
            hint = "Enter content..."
        )
    )
    val ideaContent: State<IdeaTextFieldState> = _ideaContent

    private val _events = MutableSharedFlow<UIEvents>()
    val events = _events.asSharedFlow()

    init {
        viewModelScope.launch {
            val token = getTokenUseCase()
            if (token != null) {
                _tags.addAll(getAllTagsUseCase(token))
            }
        }
    }

    fun onEvent(event: CreateIdeaEvent) {
        when (event) {
            is CreateIdeaEvent.EnteredTitle -> {
                _ideaTitle.value = _ideaTitle.value.copy(
                    text = event.value
                )
            }
            is CreateIdeaEvent.ChangeTitleFocus -> {
                _ideaTitle.value = _ideaTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && ideaTitle.value.text.isBlank()
                )
            }
            is CreateIdeaEvent.EnteredContent -> {
                _ideaContent.value = _ideaContent.value.copy(
                    text = event.value
                )
            }
            is CreateIdeaEvent.ChangeContentFocus -> {
                _ideaContent.value = _ideaContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && _ideaContent.value.text.isBlank()
                )
            }
            is CreateIdeaEvent.TagSelected -> {
                if (_selectedTags.contains(event.value)) {
                    _selectedTags.remove(event.value)
                } else {
                    _selectedTags.add(event.value)
                }
            }
            is CreateIdeaEvent.SaveIdea -> {
                viewModelScope.launch {
                    val token = getTokenUseCase()
                    val res = createIdeasUseCase(
                        title = ideaTitle.value.text,
                        content = ideaContent.value.text,
                        tags = selectedTags,
                        token = token!!
                    )

                    when (res) {
                        is ActionResult.Error -> {
                            _events.emit(UIEvents.ShowSnackBar(res.message, false))
                        }
                        is ActionResult.Success -> {
                            _ideaTitle.value = IdeaTextFieldState(
                                hint = "Enter title..."
                            )
                            _ideaContent.value = IdeaTextFieldState(
                                hint = "Enter content..."
                            )
                            _events.emit(UIEvents.ShowSnackBar("Idea created successfully! You will be redirected to the home screen in a few seconds.", true))
                        }
                    }
                }
            }
        }
    }

    sealed class UIEvents {
        data class ShowSnackBar(val message: String, val navigate: Boolean) : UIEvents()
    }
}
