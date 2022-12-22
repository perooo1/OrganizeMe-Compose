package com.plenart.organizeme_compose.ui.signUp

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.ui.signUp.mapper.SignUpMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class SignUpViewModel(mapper: SignUpMapper) : ViewModel() {

    private val _name by mutableStateOf("")
    private val _email by mutableStateOf("")
    private val _password by mutableStateOf("")

    val name: String
        get() = _name

    val email: String
        get() = _email

    val password: String
        get() = _password

    private val viewState =
        MutableStateFlow(mapper.toSignUpViewState("heromsg", name, email, password))
    val signUpViewState = viewState.asStateFlow()

}
