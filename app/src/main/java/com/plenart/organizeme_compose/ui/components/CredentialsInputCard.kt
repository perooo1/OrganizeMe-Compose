package com.plenart.organizeme_compose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.theme.LocalSpacing
import com.plenart.organizeme_compose.ui.theme.SignInSignUpButtonText

/*
data class CredentialsInputCardViewState(
    val name: String,
    val email: String,
    val password: String
)
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CredentialsInputCard(
    //credentialsInputCardViewState: CredentialsInputCardViewState,
    onEmailChange: () -> Unit,
    onPasswordChange: () -> Unit,
    onButtonAction: () -> Unit,
    modifier: Modifier = Modifier,
    signUpCredentials: Boolean = false,
    onNameChange: () -> Unit = {}
) {
    //Temporary until viewModel reference in composable screen
    val name by rememberSaveable {
        mutableStateOf("")
    }
    val email by rememberSaveable {
        mutableStateOf("")
    }
    val pwd by rememberSaveable {
        mutableStateOf("")
    }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(LocalSpacing.current.credentialsInputSpacing)
        ) {
            if (signUpCredentials) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { onNameChange() },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(id = R.string.person_icon)
                        )
                    },
                    label = { Text(text = stringResource(id = R.string.name)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.credentialsInputSpacing))
            }
            OutlinedTextField(
                value = email,
                onValueChange = { onEmailChange() },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = stringResource(id = R.string.email_icon)
                    )
                },
                label = { Text(text = stringResource(id = R.string.email)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.credentialsInputSpacing))
            OutlinedTextField(
                value = pwd,
                onValueChange = { onPasswordChange() },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock, contentDescription = stringResource(
                            id = R.string.lock_icon
                        )
                    )
                },
                label = { Text(text = stringResource(id = R.string.password)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.credentialsInputSpacing))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onButtonAction() },
            ) {
                if (signUpCredentials) {
                    Text(
                        text = stringResource(id = R.string.sign_up).uppercase(),
                        style = SignInSignUpButtonText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                } else {
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
fun CredentialsInputCardPreview() {
    //val viewstate = CredentialsInputCardViewState("null", "", "")

    CredentialsInputCard(
        onEmailChange = { },
        onPasswordChange = { },
        signUpCredentials = true,
        onNameChange = { },
        onButtonAction = {}
    )
}
