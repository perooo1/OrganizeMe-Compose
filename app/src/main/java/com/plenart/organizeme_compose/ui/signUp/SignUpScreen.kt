package com.plenart.organizeme_compose.ui.signUp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.components.CredentialsInputCard
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.ui.theme.LocalSpacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(signUpViewModel: SignUpViewModel, onNavigateToSignInScreen: () -> Unit) {
    val viewState = signUpViewModel.viewState
    val scope = rememberCoroutineScope()

    SignupScreen(
        viewState = viewState,
        onEmailChange = { signUpViewModel.onEmailChanged(it) },
        onPasswordChange = { signUpViewModel.onPasswordChanged(it) },
        onButtonAction = {
            signUpViewModel.signUp()

            if (signUpViewModel.validationSuccessful) {
                scope.launch {
                    delay(1000L)
                    onNavigateToSignInScreen()
                }
            }
        },
        onNameChange = {
            signUpViewModel.onNameChanged(it)
        }
    )
}

@Composable
fun SignupScreen(
    viewState: CredentialsInputCardViewState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onButtonAction: () -> Unit,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_hero_msg),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(LocalSpacing.current.medium, LocalSpacing.current.large)
        )
        Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
        CredentialsInputCard(
            viewState = viewState,
            onEmailChange = { onEmailChange(it) },
            onPasswordChange = { onPasswordChange(it) },
            onButtonAction = { onButtonAction() },
            signUpCredentials = true,
            onNameChange = {
                onNameChange(it)
            },
            modifier = Modifier.padding(LocalSpacing.current.medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val viewState =
        CredentialsInputCardViewState(
            "",
            "",
            ""
        )
    SignupScreen(
        viewState = viewState,
        onEmailChange = { },
        onPasswordChange = { },
        onButtonAction = { },
        onNameChange = { }
    )
}
