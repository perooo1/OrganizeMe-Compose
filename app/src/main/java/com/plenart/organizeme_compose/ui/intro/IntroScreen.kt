package com.plenart.organizeme_compose.ui.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.plenart.organizeme_compose.ui.theme.*

@Composable
fun IntroScreenRoute(
    onSignUpButtonAction: () -> Unit,
    onSignInButtonAction: () -> Unit,
) {
    IntroScreen(
        onSignUpButtonAction = onSignUpButtonAction,
        onSignInButtonAction = onSignInButtonAction
    )
}

@Composable
fun IntroScreen(
    onSignUpButtonAction: () -> Unit,
    onSignInButtonAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.intro_background),
            contentDescription = stringResource(
                id = R.string.intro_background_image
            ),
            contentScale = ContentScale.FillHeight
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = LocalSpacing.current.extraLarge)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = IntroAppName,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_task_image),
                contentDescription = stringResource(id = R.string.intro_hero_image),
                modifier = Modifier.padding(LocalSpacing.current.large)

            )
            Text(
                text = stringResource(id = R.string.intro_hero_text),
                style = IntroHeroText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    vertical = LocalSpacing.current.small
                )
            )
            Text(
                text = stringResource(id = R.string.intro_description),
                style = IntroDescription,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
            Column(
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.medium)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSignUpButtonAction() },
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up).uppercase(),
                        style = SignInSignUpButtonText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSignInButtonAction() },
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in).uppercase(),
                        style = SignInSignUpButtonText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen(
        onSignUpButtonAction = {},
        onSignInButtonAction = {}
    )
}
