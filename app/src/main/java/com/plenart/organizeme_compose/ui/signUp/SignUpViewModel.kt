package com.plenart.organizeme_compose.ui.signUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.auth.AuthenticationRepository
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.validation.Validator
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthenticationRepository,
    private val nameValidator: Validator,
    private val emailValidator: Validator,
    private val passwordValidator: Validator
) : ViewModel() {

    var viewState by mutableStateOf(CredentialsInputCardViewState())
        private set

    var validationSuccessful by mutableStateOf(false)

    fun onNameChanged(newName: String) {
        viewState = viewState.copy(name = newName)
    }

    fun onEmailChanged(newEmail: String) {
        viewState = viewState.copy(email = newEmail)
    }

    fun onPasswordChanged(newPwd: String) {
        viewState = viewState.copy(password = newPwd)
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

    fun signUp() {
        val nameValid = nameValidator.execute(viewState.email)
        val emailValid = emailValidator.execute(viewState.email)
        val passwordValid = passwordValidator.execute(viewState.password)

        if (!nameValid.successful) {
            viewState = viewState.copy(nameError = nameValid.errorMessage)
        } else {
            viewState = viewState.copy(nameError = null)
        }

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

        if (nameValid.successful && emailValid.successful && passwordValid.successful) {
            validationSuccessful = true

            viewModelScope.launch {
                authRepository.signUp(viewState.email, viewState.password)
                authRepository.createUserDocumentInCollection(
                    viewState.name,
                    viewState.email,
                    viewState.password
                )
            }
        }
    }
}
