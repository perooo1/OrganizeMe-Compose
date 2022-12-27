package com.plenart.organizeme_compose.ui.signUp.di

import com.plenart.organizeme_compose.ui.signUp.SignUpViewModel
import com.plenart.organizeme_compose.ui.signUp.mapper.SignUpMapper
import com.plenart.organizeme_compose.ui.signUp.mapper.SignUpMapperImpl
import com.plenart.organizeme_compose.validation.di.VALIDATOR_EMAIL_QUALIFIER
import com.plenart.organizeme_compose.validation.di.VALIDATOR_NAME_QUALIFIER
import com.plenart.organizeme_compose.validation.di.VALIDATOR_PASSWORD_QUALIFIER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signUpModule = module {
    viewModel {
        SignUpViewModel(
            authRepository = get(),
            nameValidator = get(named(VALIDATOR_NAME_QUALIFIER)),
            emailValidator = get(named(VALIDATOR_EMAIL_QUALIFIER)),
            passwordValidator = get(named(VALIDATOR_PASSWORD_QUALIFIER))
        )
    }
    single<SignUpMapper> { SignUpMapperImpl() }
}
