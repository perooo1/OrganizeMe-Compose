package com.plenart.organizeme_compose.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.auth.AuthenticationRepository
import com.plenart.organizeme_compose.data.board.BoardRepository
import com.plenart.organizeme_compose.data.user.UserRepository
import com.plenart.organizeme_compose.model.Board
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val STOP_TIMEOUT_MILIS = 5000L

class HomeScreenViewModel(
    private val authRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository
) : ViewModel() {

    private val userId = authRepository.currentUserId

    val userData = flow {
        emit(userRepository.getUserDetails(userId))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILIS),
        initialValue = User()
    )

    val userBoards = flow {
        emit(boardRepository.getBoardsAssignedToCurrentUser(userId))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILIS),
        initialValue = emptyList<Board>()
    )

    fun getUserInfo() {
        if (userId.isNotEmpty()) {
            viewModelScope.launch {
                userRepository.getUserDetails(userId)
            }
        }
    }

    fun createBoard() {                 //This function is currently used for testing and adding boards, ideally should be in it's own screen and according viewmodel
        if (userId.isNotEmpty()) {
            val boardName = "Compose app testing"
            val assignedUsers = mutableListOf<String>()
            assignedUsers.add(userId)

            val board =
                Board(name = boardName, createdBy = userId, assignedTo = assignedUsers.toList())

            viewModelScope.launch {
                boardRepository.createBoard(board)
            }
        }
    }
}
