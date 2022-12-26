package com.plenart.organizeme_compose.ui.signIn

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.plenart.organizeme_compose.validation.Validator

class SignInViewModel(
    private val emailValidator: Validator,
    private val passwordValidator: Validator
) : ViewModel() {

    private var _name by mutableStateOf("")
    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")

    val name: String
        get() = _name

    val email: String
        get() = _email

    val password: String
        get() = _password

    fun onEmailChanged(newEmail: String) {
        _email = newEmail
    }

    fun onPasswordChanged(newPwd: String) {
        _password = newPwd
    }

    fun logIn() {
        val emailValid = emailValidator.execute(_email)
        val passwordValid = passwordValidator.execute(_password)
        Log.i(
            "VALIDATOR",
            "Email - error: ${emailValid.errorMessage}, isValid: ${emailValid.successful}"
        )
        Log.i(
            "VALIDATOR",
            "Password - error: ${passwordValid.errorMessage}, isValid: ${passwordValid.successful}"
        )
    }
}
