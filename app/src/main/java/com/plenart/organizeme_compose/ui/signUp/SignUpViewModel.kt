package com.plenart.organizeme_compose.ui.signUp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.ui.signUp.mapper.SignUpMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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

    var credentialsInput by mutableStateOf(CredentialsInputCardViewState(_name, _email, _password))

    val signUpViewState =
        MutableStateFlow(credentialsInput).map {
            Log.i(
                "EDITTEXT",
                "VIEWMODEL - view state mapper: before mapper call: name: $_name, email: $_email, password: $_password"
            )
            Log.i(
                "EDITTEXT",
                "VIEWMODEL - view state mapper: before mapper call: name(it): ${it.name}, email(it): ${it.email}, password(it): ${it.password}"
            )
            mapper.toSignUpViewState("hero msg from viewmodel", it.name, it.email, it.password)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = SignUpViewState("initial", CredentialsInputCardViewState("", "", ""))
        )

    fun onNameChanged(newName: String) {
        Log.i(
            "EDITTEXT",
            "VIEWMODEL - onNameChanged before change: newname: $newName / old name: $_name"
        )
        //_name = newName
        credentialsInput = credentialsInput.copy(name = newName)
        Log.i(
            "EDITTEXT",
            "VIEWMODEL - onNameChanged after change: newname: $newName / old name: $_name"
        )
    }

    fun onEmailChanged(newEmail: String) {
        Log.i(
            "EDITTEXT",
            "VIEWMODEL - onEMailChanged before change: newname: $newEmail / old name: $_email"
        )
        _email = newEmail
        Log.i(
            "EDITTEXT",
            "VIEWMODEL - onEMailChanged after change: newname: $newEmail / old name: $_email"
        )
    }

    fun onPasswordChanged(newPwd: String) {
        Log.i(
            "EDITTEXT",
            "VIEWMODEL - onPasswodChanged before change: newpass: $newPwd / old name: $_password"
        )
        _password = newPwd
        Log.i(
            "EDITTEXT",
            "VIEWMODEL - onPasswodChanged after change: newpass: $newPwd / old name: $_password"
        )
    }

    fun signUp() {
        Log.i("EDITTEXT", "VIEWMODEL - login function onclick")
    }

}
