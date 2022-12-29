package com.plenart.organizeme_compose.data.auth

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun isUserAuthenticated(): Boolean
    suspend fun getAuthState(): Flow<Boolean>
    suspend fun firebaseSignUp(
        name: String,
        email: String,
        password: String
    ): Flow<AuthResponse<Boolean>>
}
