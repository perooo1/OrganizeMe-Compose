package com.plenart.organizeme_compose.data.board.di

import com.plenart.organizeme_compose.data.board.BoardRepository
import com.plenart.organizeme_compose.data.board.BoardRepositoryImpl
import org.koin.dsl.module

val boardModule = module {
    single<BoardRepository> { BoardRepositoryImpl(get()) }
}
