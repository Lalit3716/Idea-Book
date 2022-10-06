package com.example.idea_book.domain.use_cases

import com.example.idea_book.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

typealias AuthListener = Pair<String, (FirebaseUser?) -> Unit>

class AuthListenerUseCase @Inject constructor(
    authRepository: AuthRepository
) {
    private val listeners = mutableListOf<AuthListener>()

    fun addListener(tag: String, listener: (FirebaseUser?) -> Unit) {
        listeners.add(tag to listener)
    }

    fun removeListener(tag: String) {
        listeners.removeAll { it.first == tag }
    }

    private fun notifyListeners(user: FirebaseUser?) {
        listeners.forEach { it.second(user) }
    }

    init {
        authRepository.onAuthChangeListener {
            notifyListeners(it)
        }
    }
}
