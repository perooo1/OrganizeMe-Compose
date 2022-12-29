package com.plenart.organizeme_compose.ui.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.validation.Validator

class SignInViewModel(
    private val emailValidator: Validator,
    private val passwordValidator: Validator
) : ViewModel() {

    var viewState by mutableStateOf(CredentialsInputCardViewState())
        private set

    fun onEmailChanged(newEmail: String) {
        viewState = viewState.copy(email = newEmail)
    }

    fun onPasswordChanged(newPwd: String) {
        viewState = viewState.copy(password = newPwd)
    }

    fun logIn() {

        val emailValid = emailValidator.execute(viewState.email)
        val passwordValid = passwordValidator.execute(viewState.password)

        if (!emailValid.successful) {
            viewState = viewState.copy(emailError = emailValid.errorMessage)
        } else {
            viewState = viewState.copy(emailError = null)
        }



        if (!passwordValid.successful) {
            viewState = viewState.copy(passwordError = passwordValid.errorMessage)
        } else {
            viewState = viewState.copy(passwordError = null)
        }
    }
}
