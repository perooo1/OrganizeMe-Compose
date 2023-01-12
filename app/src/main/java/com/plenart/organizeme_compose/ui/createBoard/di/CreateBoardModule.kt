package com.plenart.organizeme_compose.ui.createBoard.di

import com.plenart.organizeme_compose.ui.createBoard.CreateBoardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createBoardModule = module {
    viewModel {
        CreateBoardViewModel(authRepository = get(), boardRepository = get())
    }
}
