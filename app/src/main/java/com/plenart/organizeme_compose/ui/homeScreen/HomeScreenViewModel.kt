package com.plenart.organizeme_compose.ui.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.auth.AuthenticationRepository
import com.plenart.organizeme_compose.data.board.BoardRepository
import com.plenart.organizeme_compose.data.user.UserRepository
import com.plenart.organizeme_compose.model.Board
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = User()
    )


    val userBoards = flow {
        emit(boardRepository.getBoardsAssignedToCurrentUser(userId))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )



    fun getUserInfo() {
        if (userId.isNotEmpty()) {
            viewModelScope.launch {
                userRepository.getUserDetails(userId)
            }
        }
    }

    fun createBoard() {

        if (userId.isNotEmpty()) {

            val boardName = "test board name"
            val assignedUsers = mutableListOf<String>()
            assignedUsers.add(userId)

            val board =
                Board(name = boardName, createdBy = userId, assignedTo = assignedUsers.toList())

            Log.i("HomeScreenViewModel","createBoard fun, board: ${board.toString()}")
            Log.i("HomeScreenViewModel","createBoard fun, board assigned users: ${board.assignedTo.toString()}")
            viewModelScope.launch {
                boardRepository.createBoard(board)
            }
        }

    }

}
