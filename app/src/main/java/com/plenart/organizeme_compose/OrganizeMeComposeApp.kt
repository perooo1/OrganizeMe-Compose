package com.plenart.organizeme_compose

import android.app.Application
import android.util.Log
import com.plenart.organizeme_compose.data.auth.di.authenticationRepositoryModule
import com.plenart.organizeme_compose.data.board.di.boardModule
import com.plenart.organizeme_compose.data.di.firebaseModule
import com.plenart.organizeme_compose.data.user.di.userRepositoryModule
import com.plenart.organizeme_compose.ui.homeScreen.di.homeScreenModule
import com.plenart.organizeme_compose.ui.signIn.di.signInModule
import com.plenart.organizeme_compose.ui.signUp.di.signUpModule
import com.plenart.organizeme_compose.validation.di.validationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OrganizeMeComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@OrganizeMeComposeApp)
            modules(
                signUpModule,
                signInModule,
                validationModule,
                firebaseModule,
                authenticationRepositoryModule,
                userRepositoryModule,
                homeScreenModule,
                boardModule
            )
        }
        Log.d("OrganizeMeCompose", "App started")
    }
}
