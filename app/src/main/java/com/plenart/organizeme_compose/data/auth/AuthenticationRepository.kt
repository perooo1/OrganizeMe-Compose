package com.plenart.organizeme_compose.data.auth

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun isUserAuthenticated(): Boolean
    fun getAuthState(): Flow<Boolean>
    fun firebaseSignUp(name: String, email: String, password: String): Flow<Boolean>
}
