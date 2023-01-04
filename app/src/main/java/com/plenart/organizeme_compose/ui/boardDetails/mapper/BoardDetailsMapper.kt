package com.plenart.organizeme_compose.ui.boardDetails.mapper

import com.plenart.organizeme_compose.model.Board
import com.plenart.organizeme_compose.ui.boardDetails.BoardDetailsViewState

interface BoardDetailsMapper {
    fun toBoardDetailsViewState(board: Board): BoardDetailsViewState
}
