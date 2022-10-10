package com.example.idea_book.presentation.ideas

import com.example.idea_book.domain.model.IdeaModel

data class IdeasScreenState(
    val isLoading: Boolean = true,
    val ideas: List<IdeaModel> = emptyList(),
    val error: String = ""
)
