package com.example.idea_book.domain.use_cases.ideas

import com.example.idea_book.domain.repository.IdeasRepository
import javax.inject.Inject

class GetIdeasUseCase @Inject constructor(
    private val ideasRepository: IdeasRepository
) {
    suspend operator fun invoke(token: String) = ideasRepository.getIdeas(token)
}
