package com.plenart.organizeme_compose.data.auth.di

import com.plenart.organizeme_compose.data.auth.AuthenticationRepository
import com.plenart.organizeme_compose.data.auth.AuthenticationRepositoryImpl
import org.koin.dsl.module

val authenticationRepositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            auth = get(),
            firestore = get()
        )
    }
}
