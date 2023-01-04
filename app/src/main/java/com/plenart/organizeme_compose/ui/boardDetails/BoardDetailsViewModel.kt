package com.plenart.organizeme_compose.ui.boardDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.board.BoardRepository
import com.plenart.organizeme_compose.model.Board
import com.plenart.organizeme_compose.ui.boardDetails.mapper.BoardDetailsMapper
import kotlinx.coroutines.flow.*

private const val STOP_TIMEOUT_MILIS = 5000L

class BoardDetailsViewModel(
    private val boardId: String,
    private val boardRepository: BoardRepository,
    private val mapper: BoardDetailsMapper
) : ViewModel() {

    private val boardData = flow {
        emit(boardRepository.getBoardById(boardId))
    }

    val boardDetailsViewState: StateFlow<BoardDetailsViewState> = boardData.map { board ->
        mapper.toBoardDetailsViewState(
            board ?: Board(
                name = "Board from flow is null",
                createdBy = "null",
                documentID = "null-nada!"
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILIS),
        initialValue = BoardDetailsViewState("", "", "")
    )
}
