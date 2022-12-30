package com.plenart.organizeme_compose.data.user

import com.plenart.organizeme_compose.data.auth.AuthResponse
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserDetails(userId: String): Flow<AuthResponse<User>>
}
