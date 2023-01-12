package com.plenart.organizeme_compose.data.user

import com.plenart.organizeme_compose.model.User

interface UserRepository {
    suspend fun getUserDetails(userId: String): User?
}
