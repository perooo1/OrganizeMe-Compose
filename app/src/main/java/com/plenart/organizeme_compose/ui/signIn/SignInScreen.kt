package com.plenart.organizeme_compose.ui.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.components.CredentialsInputCard
import com.plenart.organizeme_compose.ui.components.CredentialsInputCardViewState
import com.plenart.organizeme_compose.ui.theme.LocalSpacing
import com.plenart.organizeme_compose.ui.theme.SignUpSignInDescription

@Composable
fun SignInRoute(viewModel: SignInViewModel, onNavigateToHomeScreen: () -> Unit) {
    val viewState = viewModel.viewState

    SignInScreen(
        credentialsInputCardViewState = viewState,
        onEmailChange = { viewModel.onEmailChanged(it) },
        onPasswordChange = { viewModel.onPasswordChanged(it) },
        onButtonAction = {
            viewModel.signIn()
            onNavigateToHomeScreen()
        }
    )
}

@Composable
fun SignInScreen(
    credentialsInputCardViewState: CredentialsInputCardViewState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onButtonAction: () -> Unit,
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.intro_background),
            contentDescription = stringResource(
                id = R.string.intro_background_image
            ),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(LocalSpacing.current.extraLarge))
            Text(
                text = stringResource(id = R.string.sign_in_hero_msg),
                style = SignUpSignInDescription,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    start = LocalSpacing.current.medium,
                    top = LocalSpacing.current.extraLarge,
                    end = LocalSpacing.current.medium,
                    bottom = LocalSpacing.current.large
                )
            )
            Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
            CredentialsInputCard(
                viewState = credentialsInputCardViewState,
                onEmailChange = { onEmailChange(it) },
                onPasswordChange = { onPasswordChange(it) },
                onButtonAction = { onButtonAction() },
                signUpCredentials = false,
                onNameChange = { onNameChange(it) },
                modifier = Modifier.padding(LocalSpacing.current.medium)
            )
        }
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
