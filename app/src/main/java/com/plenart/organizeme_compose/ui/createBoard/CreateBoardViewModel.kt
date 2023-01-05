package com.plenart.organizeme_compose.ui.createBoard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.auth.AuthenticationRepository
import com.plenart.organizeme_compose.data.board.BoardRepository
import com.plenart.organizeme_compose.model.Board
import kotlinx.coroutines.launch

class CreateBoardViewModel(
    private val authRepository: AuthenticationRepository,
    private val boardRepository: BoardRepository
) : ViewModel() {
    var viewState by mutableStateOf(CreateBoardViewState())
        private set

    private val currentUserId = authRepository.currentUserId

    fun onBoardNameChanged(name: String) {
        viewState = viewState.copy(boardName = name)
    }

    fun createBoard() {
        if (currentUserId.isNotEmpty()) {
            val boardName = viewState.boardName
            val assignedUsers = mutableListOf<String>()
            assignedUsers.add(currentUserId)

            val board =
                Board(
                    name = boardName,
                    createdBy = currentUserId,
                    assignedTo = assignedUsers.toList()
                )

            viewModelScope.launch {
                boardRepository.createBoard(board)
            }
        }
    }
}
