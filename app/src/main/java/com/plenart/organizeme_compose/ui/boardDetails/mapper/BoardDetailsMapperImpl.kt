package com.plenart.organizeme_compose.ui.boardDetails.mapper

import com.plenart.organizeme_compose.model.Board
import com.plenart.organizeme_compose.ui.boardDetails.BoardDetailsViewState

class BoardDetailsMapperImpl : BoardDetailsMapper {
    override fun toBoardDetailsViewState(board: Board): BoardDetailsViewState =
        BoardDetailsViewState(
            board.documentID,
            board.name,
            board.createdBy
        )
}
