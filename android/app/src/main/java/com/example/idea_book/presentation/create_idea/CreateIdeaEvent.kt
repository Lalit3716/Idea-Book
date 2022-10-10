package com.example.idea_book.presentation.create_idea

import androidx.compose.ui.focus.FocusState

sealed class CreateIdeaEvent{
    data class EnteredTitle(val value: String): CreateIdeaEvent()
    data class ChangeTitleFocus(val focusState: FocusState): CreateIdeaEvent()
    data class EnteredContent(val value: String): CreateIdeaEvent()
    data class ChangeContentFocus(val focusState: FocusState): CreateIdeaEvent()
    data class ChangeColor(val color: Int): CreateIdeaEvent()
    object SaveIdea: CreateIdeaEvent()
}
