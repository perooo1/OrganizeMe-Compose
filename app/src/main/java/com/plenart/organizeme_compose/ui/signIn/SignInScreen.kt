package com.plenart.organizeme_compose.ui.signIn

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
fun SignInScreen(
    credentialsInputCardViewState: CredentialsInputCardViewState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onButtonAction: () -> Unit,
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_hero_msg),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(LocalSpacing.current.medium, LocalSpacing.current.large)
        )
        Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
        CredentialsInputCard(
            viewState = credentialsInputCardViewState,
            onEmailChange = { onEmailChange(it) },
            onPasswordChange = { onPasswordChange(it) },
            onButtonAction = { onButtonAction() },
            signUpCredentials = false,
            onNameChange = {onNameChange(it) },
            modifier = Modifier.padding(LocalSpacing.current.medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    val viewState = CredentialsInputCardViewState()

    SignInScreen(
        credentialsInputCardViewState = viewState,
        onEmailChange = { },
        onPasswordChange = { },
        onButtonAction = { },
        onNameChange = { },
    )
}
