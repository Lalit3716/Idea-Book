package com.example.idea_book.presentation.ideas

import com.example.idea_book.domain.model.IdeaModel

sealed class IdeasScreenEvent {
    data class DeleteIdea(val idea: IdeaModel): IdeasScreenEvent()
    object RestoreIdea: IdeasScreenEvent()
}
