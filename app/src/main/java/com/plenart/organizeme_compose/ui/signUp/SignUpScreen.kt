package com.plenart.organizeme_compose.ui.signUp

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
    onEmailChange: () -> Unit,
    onPasswordChange: () -> Unit,
    onButtonAction: () -> Unit,
    onNameChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_hero_msg),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = LocalSpacing.current.large)
        )
        Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
        CredentialsInputCard(
            onEmailChange = { onEmailChange() },
            onPasswordChange = { onPasswordChange() },
            onButtonAction = { onButtonAction() },
            signUpCredentials = true,
            onNameChange = { onNameChange() },
            modifier =  Modifier.padding(LocalSpacing.current.medium)
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
