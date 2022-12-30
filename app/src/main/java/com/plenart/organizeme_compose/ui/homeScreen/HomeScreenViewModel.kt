package com.plenart.organizeme_compose.ui.homeScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.auth.AuthResponse
import com.plenart.organizeme_compose.data.auth.AuthenticationRepository
import com.plenart.organizeme_compose.data.user.UserRepository
import com.plenart.organizeme_compose.model.User
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val authRepository: AuthenticationRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val userId = authRepository.currentUser?.uid

    private val _userData =
        mutableStateOf<AuthResponse<User>>(AuthResponse.Success(User("empty")))    //potential nullable user!

    val userData: State<AuthResponse<User>> = _userData

    fun getUserInfo() {
        Log.i("HomeScreenViewModel", "first call inside getUserInfo: userId: $userId")
        Log.i("HomeScreenViewModel", "first call inside getUserInfo: _userData: ${_userData.value}")
        if (userId != null) {
            Log.i("HomeScreenViewModel", "inside getUserInfo != null if call: userId: $userId")
            viewModelScope.launch {
                Log.i("HomeScreenViewModel", "inside viewmodelscope")
                userRepository.getUserDetails(userId).collect {
                    Log.i(
                        "HomeScreenViewModel",
                        "inside viewmodelscope inside collect: _userData: ${_userData.value}"
                    )
                    Log.i(
                        "HomeScreenViewModel",
                        "inside viewmodelscope inside collect: _userData: ${_userData.value}, it: ${it.toString()}"
                    )
                    _userData.value = it
                    Log.i(
                        "HomeScreenViewModel",
                        "inside viewmodelscope inside collect, after assign: _userData: ${_userData.value}, it: ${it.toString()}"
                    )
                }
                Log.i("HomeScreenViewModel", "inside viewmodelscope, after resporitory call")
                Log.i(
                    "HomeScreenViewModel",
                    "inside viewmodelscope, after resporitory call: _userData: ${_userData.value}"
                )
            }
        }
    }
}
