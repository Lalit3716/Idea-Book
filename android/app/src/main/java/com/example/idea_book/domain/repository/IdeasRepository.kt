package com.example.idea_book.domain.repository

import com.example.idea_book.domain.model.IdeaModel

interface IdeasRepository {
    suspend fun getIdeas(token: String): List<IdeaModel>
    suspend fun createIdea(title: String, description: String, color: Int, token: String): Boolean
}