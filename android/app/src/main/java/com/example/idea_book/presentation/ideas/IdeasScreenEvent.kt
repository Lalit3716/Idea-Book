package com.example.idea_book.presentation.ideas

data class IdeasScreenEvent(
    val IdeaClick: (Int) -> Unit,
    val IdeaDeleteClick: (Int) -> Unit
)
