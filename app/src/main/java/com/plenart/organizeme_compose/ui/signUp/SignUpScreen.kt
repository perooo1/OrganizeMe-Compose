package com.plenart.organizeme_compose.ui.signUp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.components.CredentialsInputCard
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.ui.theme.LocalSpacing

@Composable
fun SignupScreen(
    viewState: SignUpViewState,
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
            email = viewState.credentialsInputCardViewState.email,
            password = viewState.credentialsInputCardViewState.password,
            onEmailChange = { onEmailChange(it) },
            onPasswordChange = { onPasswordChange(it) },
            onButtonAction = { onButtonAction() },
            signUpCredentials = true,
            name = viewState.credentialsInputCardViewState.name,
            onNameChange = {
                Log.i(
                    "EDITTEXT",
                    "SignUpScreen: onValueChange, before function call: name: ${viewState.credentialsInputCardViewState.name} "
                )
                Log.i(
                    "EDITTEXT",
                    "SignUpScreen: onValueChange, before function call: name(it): $it "
                )

                onNameChange(it)
                Log.i(
                    "EDITTEXT",
                    "SignUpScreen: onValueChange, after function call: name: ${viewState.credentialsInputCardViewState.name} "
                )
                Log.i(
                    "EDITTEXT",
                    "SignUpScreen: onValueChange, after function call: name(it): $it "
                )
            },
            modifier = Modifier.padding(LocalSpacing.current.medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {

    val viewState =
        SignUpViewState(
            "This is a hero msg",
            CredentialsInputCardViewState(
                "",
                "",
                ""
            )
        )

    SignupScreen(
        viewState = viewState,
        onEmailChange = { },
        onPasswordChange = { },
        onButtonAction = { },
        onNameChange = { },
    )
}
