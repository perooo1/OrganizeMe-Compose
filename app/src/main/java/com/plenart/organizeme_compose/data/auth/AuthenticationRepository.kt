package com.plenart.organizeme_compose.data.auth

import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    val isUserAuthenticated: Boolean
    val currentUserId: String

    val currentUser: Flow<User>

    suspend fun signUp(email: String, password: String)
    suspend fun createUserDocumentInCollection(name: String, email: String, password: String)
    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
}
