package com.example.idea_book.domain.model

import com.example.idea_book.ui.theme.*

data class IdeaModel(
    val id: Int,
    val title: String,
    val description: String,
    val username: String,
    val user_id: String,
    val likes_total: Int,
    val comments_total: Int,
    val tags: List<TagModel>
)

data class TagModel(
    val id: Int,
    val name: String,
    val icon: String
)
