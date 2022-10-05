package com.example.idea_book.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun singUp(username: String, email: String, password: String): Boolean
    suspend fun signOut(): Boolean
    fun isAuth(): Boolean
    fun getUser(): FirebaseUser
    fun onAuthChangeListener(listener: (FirebaseUser?) -> Unit)
}
