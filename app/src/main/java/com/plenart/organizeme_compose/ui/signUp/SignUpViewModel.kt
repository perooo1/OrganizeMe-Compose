package com.plenart.organizeme_compose.ui.signUp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.ui.signUp.mapper.SignUpMapper
import kotlinx.coroutines.flow.*

class SignUpViewModel(mapper: SignUpMapper) : ViewModel() {

    private var _name by mutableStateOf("pocetno")
    private var _email by mutableStateOf("pocetno")
    private var _password by mutableStateOf("prvo")

    val name: String
        get() = _name

    val email: String
        get() = _email

    val password: String
        get() = _password

    val signUpViewState =
        MutableStateFlow(CredentialsInputCardViewState(_name, _email, _password)).map {
            mapper.toSignUpViewState("hero msg from viewmodel", it.name, it.email, it.password)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = SignUpViewState("initial", CredentialsInputCardViewState("", "", ""))
        )

    fun onNameChanged(newName: String) {
        Log.i("VIEWMODEL", "onNameChanged: newname: $newName / old name: $_name")
        _name = newName
    }

    fun onEmailChanged(newEmail: String) {
        Log.i("VIEWMODEL", "onEMailChanged: newname: $newEmail / old name: $_email")
        _email = newEmail
    }

    fun onPasswordChanged(newPwd: String) {
        Log.i("VIEWMODEL", "onPasswodChanged: newpass: $newPwd / old name: $_password")
        _password = newPwd
    }

    fun logIn() {

    }

}
