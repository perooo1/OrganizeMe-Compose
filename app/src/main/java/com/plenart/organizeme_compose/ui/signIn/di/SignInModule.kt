package com.plenart.organizeme_compose.ui.signIn.di

import com.plenart.organizeme_compose.ui.signIn.SignInViewModel
import com.plenart.organizeme_compose.validation.di.VALIDATOR_EMAIL_QUALIFIER
import com.plenart.organizeme_compose.validation.di.VALIDATOR_PASSWORD_QUALIFIER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signInModule = module {
    viewModel {
        SignInViewModel(
            authRepository = get(),
            emailValidator = get(named(VALIDATOR_EMAIL_QUALIFIER)),
            passwordValidator = get(named(VALIDATOR_PASSWORD_QUALIFIER))
        )
    }
}
