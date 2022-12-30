package com.plenart.organizeme_compose.ui.homeScreen.di

import com.plenart.organizeme_compose.ui.homeScreen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeScreenModule = module {
    viewModel {
        HomeScreenViewModel(authRepository = get(), userRepository = get())
    }
}
