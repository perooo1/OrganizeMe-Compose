package com.plenart.organizeme_compose.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val firebaseModule = module {
    single<FirebaseAuth>{FirebaseAuth.getInstance()}
    single<FirebaseFirestore>{ FirebaseFirestore.getInstance()}
}
