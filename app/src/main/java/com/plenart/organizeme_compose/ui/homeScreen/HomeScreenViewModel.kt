package com.plenart.organizeme_compose.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.auth.AuthenticationRepository
import com.plenart.organizeme_compose.data.user.UserRepository
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val authRepository: AuthenticationRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val userId = authRepository.currentUserId

    val userData = flow {
        emit(userRepository.getUserDetails(userId))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = User()
    )

    fun getUserInfo() {
        if (userId.isNotEmpty()) {
            viewModelScope.launch {
                userRepository.getUserDetails(userId)
            }
        }
    }
}
