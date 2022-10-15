package com.example.idea_book.domain.use_cases.ideas

import com.example.idea_book.domain.model.TagModel
import com.example.idea_book.domain.repository.IdeasRepository
import javax.inject.Inject

class CreateIdeaUseCase @Inject constructor(
    private val ideasRepository: IdeasRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String,
        tags: List<TagModel>,
        token: String
    ): Boolean {
        ideasRepository.createIdea(
            title = title,
            description = content,
            tags = tags,
            token = token
        )

        return true
    }
}
