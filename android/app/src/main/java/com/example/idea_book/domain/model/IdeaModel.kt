package com.example.idea_book.domain.model

import com.example.idea_book.ui.theme.*

data class IdeaModel(
    val id: Int,
    val title: String,
    val description: String,
    val username: String,
    val user_id: String,
    val color: Int
) {
    companion object {
        val ideaColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
