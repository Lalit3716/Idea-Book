package com.example.idea_book.data.repository

import com.example.idea_book.data.IdeaBookApi
import com.example.idea_book.data.dto.request.IdeaReq
import com.example.idea_book.data.dto.response.IdeaRes
import com.example.idea_book.data.dto.response.toModel
import com.example.idea_book.domain.model.IdeaModel
import com.example.idea_book.domain.repository.IdeasRepository
import javax.inject.Inject

class IdeasRepositoryImpl @Inject constructor(
    private val ideaBookApi: IdeaBookApi
) : IdeasRepository {
    override suspend fun getIdeas(token: String): List<IdeaModel> {
        return ideaBookApi.getIdeas(token).map { it.toModel() }
    }

    override suspend fun createIdea(
        title: String,
        description: String,
        color: Int,
        token: String
    ): Boolean {
        val ideaReq = IdeaReq(
            title = title,
            description = description,
            color = color
        )
        ideaBookApi.createIdea(ideaReq, token)
        return true
    }
}
