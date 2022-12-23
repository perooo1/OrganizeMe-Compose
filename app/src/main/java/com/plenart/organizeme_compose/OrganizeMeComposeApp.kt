package com.plenart.organizeme_compose

import android.app.Application
import android.util.Log
import com.plenart.organizeme_compose.ui.signUp.di.signUpModule
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
                signUpModule
            )
        }
        Log.d("OrganizeMeCompose", "App started")
    }
}
