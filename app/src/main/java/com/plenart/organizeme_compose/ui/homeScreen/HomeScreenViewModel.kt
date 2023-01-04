package com.plenart.organizeme_compose.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.auth.AuthenticationRepository
import com.plenart.organizeme_compose.data.board.BoardRepository
import com.plenart.organizeme_compose.data.user.UserRepository
import com.plenart.organizeme_compose.model.Board
import com.plenart.organizeme_compose.model.User
import com.plenart.organizeme_compose.ui.homeScreen.mapper.HomeScreenMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val STOP_TIMEOUT_MILIS = 5000L

class HomeScreenViewModel(
    private val authRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
    private val mapper: HomeScreenMapper
) : ViewModel() {

    private val userId = authRepository.currentUserId

    val userData = flow {
        emit(userRepository.getUserDetails(userId))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILIS),
        initialValue = User()
    )

    private val userBoards = flow {
        emit(boardRepository.getBoardsAssignedToCurrentUser(userId))
    }

    val homeScreenViewState: StateFlow<HomeScreenViewState> =
        userBoards.map { boards ->
            mapper.toHomeScreenViewState(boards)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILIS),
            initialValue = HomeScreenViewState(emptyList())
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
            val boardName = "Nakon board detailsaa"
            val assignedUsers = mutableListOf<String>()
            assignedUsers.add(userId)

            val board =
                Board(name = boardName, createdBy = userId, assignedTo = assignedUsers.toList())

            viewModelScope.launch {
                boardRepository.createBoard(board)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }
}
