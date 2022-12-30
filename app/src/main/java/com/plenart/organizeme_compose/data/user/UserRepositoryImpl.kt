package com.plenart.organizeme_compose.data.user

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.plenart.organizeme_compose.data.auth.AuthResponse
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

const val FIRESTORE_COLLECTION_USERS = "Users"

class UserRepositoryImpl(private val firestore: FirebaseFirestore) : UserRepository {
    override suspend fun getUserDetails(userId: String): Flow<AuthResponse<User>> = callbackFlow {
        Log.i("UserRepositoryImpl", "first call inside getUserDetails fun")
        val snapshotListener = firestore.collection(FIRESTORE_COLLECTION_USERS)
            .document(userId)
            .addSnapshotListener { documentSnapshot, firebaseException ->
                Log.i(
                    "UserRepositoryImpl",
                    "inside getUserDetails, inside addSnapshotListener: snapshot: ${documentSnapshot?.data.toString()}, exception: ${firebaseException?.localizedMessage.toString()}"
                )
                val response = if (documentSnapshot != null) {
                    val signedInUser = documentSnapshot.toObject(User::class.java)
                    Log.i("UserRepositoryImpl","signedInUser: ${signedInUser.toString()}")
                    AuthResponse.Success(signedInUser!!)
                } else {
                    AuthResponse.Error(firebaseException?.localizedMessage.toString())
                }
                trySend(response)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }
}
