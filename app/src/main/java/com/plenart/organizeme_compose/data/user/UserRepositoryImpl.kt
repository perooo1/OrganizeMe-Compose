package com.plenart.organizeme_compose.data.user

import com.google.firebase.firestore.FirebaseFirestore
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_USERS = "Users"

class UserRepositoryImpl(private val firestore: FirebaseFirestore) : UserRepository {

    override suspend fun getUserDetails(userId: String): User? =
        firestore.collection(FIRESTORE_COLLECTION_USERS)
            .document(userId)
            .get()
            .await()
            .toObject(User::class.java)

}
