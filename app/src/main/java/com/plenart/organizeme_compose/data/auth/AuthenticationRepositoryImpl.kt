package com.plenart.organizeme_compose.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.plenart.organizeme_compose.data.user.FIRESTORE_COLLECTION_USERS
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthenticationRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthenticationRepository {

    override val isUserAuthenticated: Boolean
        get() = auth.currentUser != null

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                trySend(auth.currentUser?.let { User(id = it.uid) } ?: User())
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun createUserDocumentInCollection(
        name: String,
        email: String,
        password: String
    ) {
        val userObj = if (currentUserId.isNotEmpty()) {
            User(id = currentUserId, name = name, email = email, password = password)
        } else {
            throw IllegalArgumentException("createUserDocumentInCollection error, user obj not created due to id being empty")
        }

        firestore.collection(FIRESTORE_COLLECTION_USERS)
            .document(currentUserId)
            .set(userObj)
            .await()
    }

    override suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}
