package com.example.idea_book.data.dto.response

import com.example.idea_book.domain.model.IdeaModel

data class IdeaRes(
    val CreatedAt: String,
    val DeletedAt: Any,
    val ID: Int,
    val UpdatedAt: String,
    val description: String,
    val title: String,
    val user_id: String,
    val username: String,
    val color: Int
)

fun IdeaRes.toModel(): IdeaModel {
    return IdeaModel(
        id = ID,
        title = title,
        description = description,
        username = username,
        user_id = user_id,
        color = color
    )
}
