package com.example.idea_book.data.dto.response

import com.example.idea_book.domain.model.IdeaModel
import com.example.idea_book.domain.model.TagModel

data class IdeaRes(
    val CreatedAt: String,
    val DeletedAt: Any,
    val ID: Int,
    val UpdatedAt: String,
    val description: String,
    val title: String,
    val user_id: String,
    val username: String,
    val likes_total: Int,
    val comments_total: Int,
    val tags: List<TagModel>
)

fun IdeaRes.toModel(): IdeaModel {
    return IdeaModel(
        id = ID,
        title = title,
        description = description,
        username = username,
        user_id = user_id,
        likes_total = likes_total,
        comments_total = comments_total,
        tags = tags
    )
}
