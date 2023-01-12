package com.plenart.organizeme_compose.ui.homeScreen.mapper

import com.plenart.organizeme_compose.model.Board
import com.plenart.organizeme_compose.ui.components.ItemBoardViewState
import com.plenart.organizeme_compose.ui.homeScreen.HomeScreenViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeScreenViewState(boards: List<Board>): HomeScreenViewState {
        return HomeScreenViewState(
            boards.map { board ->
                ItemBoardViewState(board.documentID, board.name, board.createdBy)
            }
        )
    }
}
