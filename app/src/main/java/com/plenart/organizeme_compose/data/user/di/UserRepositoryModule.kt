package com.plenart.organizeme_compose.data.user.di

import com.plenart.organizeme_compose.data.user.UserRepository
import com.plenart.organizeme_compose.data.user.UserRepositoryImpl
import org.koin.dsl.module

val userRepositoryModule = module {
    single<UserRepository> {
        UserRepositoryImpl(firestore = get())
    }
}
