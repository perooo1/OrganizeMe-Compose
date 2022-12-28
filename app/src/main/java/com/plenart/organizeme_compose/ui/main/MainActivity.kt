package com.plenart.organizeme_compose.ui.main

import android.os.Bundle
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
/*
                val viewModel: SignInViewModel = getViewModel()
                val viewState = viewModel.viewState

                SignInScreen(
                    credentialsInputCardViewState = viewState,
                    onEmailChange = { viewModel.onEmailChanged(it) },
                    onPasswordChange = { viewModel.onPasswordChanged(it) },
                    onButtonAction = { viewModel.logIn() }
                )

 */

                /*
                val viewModel: SignUpViewModel = getViewModel()
                val viewState = viewModel.viewState

                SignupScreen(
                    viewState = viewState,
                    onEmailChange = { viewModel.onEmailChanged(it) },
                    onPasswordChange = { viewModel.onPasswordChanged(it) },
                    onButtonAction = { viewModel.signUp() },
                    onNameChange = {
                        viewModel.onNameChanged(it)
                    }
                )
                 */

                MainScreen()



            }
        }
    }
}
