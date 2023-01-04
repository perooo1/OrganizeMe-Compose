package com.plenart.organizeme_compose.ui.homeScreen.mapper

import com.plenart.organizeme_compose.model.Board
import com.plenart.organizeme_compose.ui.homeScreen.HomeScreenViewState

interface HomeScreenMapper {
    fun toHomeScreenViewState(boards: List<Board>): HomeScreenViewState
}
