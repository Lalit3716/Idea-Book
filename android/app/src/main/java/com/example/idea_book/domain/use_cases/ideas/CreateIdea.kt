package com.example.idea_book.domain.use_cases.ideas

import com.example.idea_book.domain.repository.IdeasRepository
import javax.inject.Inject

class CreateIdeaUseCase @Inject constructor(
    private val ideasRepository: IdeasRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String,
        color: Int,
        token: String
    ): Boolean {
        ideasRepository.createIdea(
            title = title,
            description = content,
            color = color,
            token = token
        )

        return true
    }
}
