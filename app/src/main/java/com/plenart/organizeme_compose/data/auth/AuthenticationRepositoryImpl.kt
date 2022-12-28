package com.plenart.organizeme_compose.data.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_USERS = "Users"

class AuthenticationRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthenticationRepository {

    var operationSuccessful = false

    override fun isUserAuthenticated(): Boolean = auth.currentUser != null

    override fun getAuthState(): Flow<Boolean> = callbackFlow {
        Log.i("TAG","Current user: ${auth.currentUser.toString()}")

        val authStateListener = AuthStateListener {
            trySend(auth.currentUser == null)           //add try catch block
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseSignUp(
        name: String,
        email: String,
        password: String
    ): Flow<Boolean> = flow {
        operationSuccessful = false

        try {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
                Log.i(
                    "FIREBASE",
                    "AuthRepoImpl, signup success listener, operationsucces: $operationSuccessful"
                )
            }.await()

            if (operationSuccessful) {
                val userId = auth.currentUser?.uid!!
                val userObj = User(id = userId, name = name, email = email, password = password)
                firestore.collection(FIRESTORE_COLLECTION_USERS).document(userId).set(userObj)
                    .addOnSuccessListener {
                        Log.i(
                            "FIREBASE",
                            "AuthRepoImpl,firestore document add success listener, operationsucces: ${userObj.toString()}"
                        )
                    }.await()
                emit(operationSuccessful)
            } else {
                emit(operationSuccessful)
            }

        } catch (e: FirebaseAuthException) {
            Log.i(
                "FIREBASE",
                "AuthRepoImpl,ERROR: ${e.errorCode}, msg hehe: ${e.localizedMessage}"
            )
            emit(false)
        }
    }
}
