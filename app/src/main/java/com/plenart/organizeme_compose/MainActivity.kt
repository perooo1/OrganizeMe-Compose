package com.plenart.organizeme_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.plenart.organizeme_compose.ui.signIn.SignInScreen
import com.plenart.organizeme_compose.ui.signIn.SignInViewModel
import com.plenart.organizeme_compose.ui.signUp.SignUpViewModel
import com.plenart.organizeme_compose.ui.signUp.SignupScreen
import com.plenart.organizeme_compose.ui.theme.OrganizeMeComposeTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrganizeMeComposeTheme {
                val viewModel: SignInViewModel = getViewModel()

                SignInScreen(
                    email = viewModel.email,
                    password = viewModel.password,
                    onEmailChange = { viewModel.onEmailChanged(it) },
                    onPasswordChange = { viewModel.onPasswordChanged(it) },
                    onButtonAction = { viewModel.logIn() }
                )


/*
                val viewModel: SignUpViewModel = getViewModel()
                val viewState by viewModel.signUpViewState.collectAsState()

                SignupScreen(
                    viewState = viewState,
                    onEmailChange = { viewModel.onEmailChanged(it) },
                    onPasswordChange = { viewModel.onPasswordChanged(it) },
                    onButtonAction = { viewModel.signUp() },
                    onNameChange = {
                        Log.i(
                            "EDITTEXT",
                            "MainActivity: onValueChange, before function call: name:${viewState.credentialsInputCardViewState.name}  "
                        )
                        Log.i(
                            "EDITTEXT",
                            "MainActivity: onValueChange, before function call: name(it):${it}  "
                        )
                        viewModel.onNameChanged(it)
                        Log.i(
                            "EDITTEXT",
                            "MainActivity: onValueChange, after function call: name:${viewState.credentialsInputCardViewState.name}  "
                        )
                        Log.i(
                            "EDITTEXT",
                            "MainActivity: onValueChange, after function call: name(it):${it}  "
                        )
                    }
                )
*/


            }
        }
    }
}
