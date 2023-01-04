package com.plenart.organizeme_compose.ui.boardDetails.di

import com.plenart.organizeme_compose.ui.boardDetails.BoardDetailsViewModel
import com.plenart.organizeme_compose.ui.boardDetails.mapper.BoardDetailsMapper
import com.plenart.organizeme_compose.ui.boardDetails.mapper.BoardDetailsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val boardDetailsModule = module {
    viewModel { params ->
        BoardDetailsViewModel(
            boardId = params.get(),
            boardRepository = get(),
            mapper = get()
        )
    }
    single<BoardDetailsMapper> { BoardDetailsMapperImpl() }
}
