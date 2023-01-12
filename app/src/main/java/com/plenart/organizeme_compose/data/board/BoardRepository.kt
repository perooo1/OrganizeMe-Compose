package com.plenart.organizeme_compose.data.board

import com.plenart.organizeme_compose.model.Board

interface BoardRepository {
    suspend fun createBoard(board: Board)
    suspend fun getBoardsAssignedToCurrentUser(userId: String): List<Board>
    suspend fun getBoardById(boardId: String): Board?
}
