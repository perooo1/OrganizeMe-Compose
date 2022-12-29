package com.plenart.organizeme_compose.ui.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plenart.organizeme_compose.data.auth.AuthResponse
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

    private val userAuthenticated = authRepository.isUserAuthenticated()

    private val _userSignUpSuccess =
        mutableStateOf<AuthResponse<Boolean>>(AuthResponse.Success(false))
    val userSignUpSuccess: State<AuthResponse<Boolean>> = _userSignUpSuccess

    private val _firebaseAuthState = mutableStateOf(false)
    val firebaseAuthState: State<Boolean> = _firebaseAuthState


    fun onNameChanged(newName: String) {
        viewState = viewState.copy(name = newName)
    }

    fun onEmailChanged(newEmail: String) {
        viewState = viewState.copy(email = newEmail)
    }

    fun onPasswordChanged(newPwd: String) {
        viewState = viewState.copy(password = newPwd)
    }

    fun getFirebaseAuthState() {
        viewModelScope.launch {
            authRepository.getAuthState().collect {
                _firebaseAuthState.value = it
            }
        }
    }

    fun signUp() {

        val nameValid = nameValidator.execute(viewState.email)
        val emailValid = emailValidator.execute(viewState.email)
        val passwordValid = passwordValidator.execute(viewState.password)


        //todo refactor!!

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
            viewModelScope.launch {
                authRepository.firebaseSignUp(viewState.name, viewState.email, viewState.password)
                    .collect {
                        _userSignUpSuccess.value = it
                    }
            }
        }
    }
}
