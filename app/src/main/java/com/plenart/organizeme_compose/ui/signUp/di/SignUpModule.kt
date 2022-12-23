package com.plenart.organizeme_compose.ui.signUp.di

import com.plenart.organizeme_compose.ui.signUp.SignUpViewModel
import com.plenart.organizeme_compose.ui.signUp.mapper.SignUpMapper
import com.plenart.organizeme_compose.ui.signUp.mapper.SignUpMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpModule = module {
    viewModel {
        SignUpViewModel(get())
    }
    single<SignUpMapper> { SignUpMapperImpl() }
}
