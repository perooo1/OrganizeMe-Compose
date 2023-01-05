package com.plenart.organizeme_compose.ui.createBoard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.components.navigationComponents.topBar.TopBar
import com.plenart.organizeme_compose.ui.components.navigationComponents.topBar.TopBarIcon
import com.plenart.organizeme_compose.ui.theme.IntroDescription
import com.plenart.organizeme_compose.ui.theme.LocalSpacing
import com.plenart.organizeme_compose.ui.theme.SignInSignUpButtonText

@Composable
fun CreateBoardRoute(
    viewModel: CreateBoardViewModel,
    onNavigateToHomeScreen: () -> Unit,
    onTopBarNavigationAction: () -> Unit
) {
    val viewState = viewModel.viewState

    CreateBoardScreen(
        viewState = viewState,
        onBoardNameChange = {
            viewModel.onBoardNameChanged(it)
        },
        onCreateBoardButtonAction = {
            viewModel.createBoard()
            onNavigateToHomeScreen()
        },
        onTopBarNavigationAction = { onTopBarNavigationAction() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBoardScreen(
    viewState: CreateBoardViewState,
    onBoardNameChange: (String) -> Unit,
    onCreateBoardButtonAction: () -> Unit,
    onTopBarNavigationAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(navigationIcon = {
                TopBarIcon(
                    onIconAction = {
                        onTopBarNavigationAction()
                    },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.top_bar_menu_icon)
                )
            })
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.padding(top = LocalSpacing.current.extraLarge))
            Text(
                text = stringResource(id = R.string.create_board_instructions),
                style = IntroDescription,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(LocalSpacing.current.medium)
            )
            Card(modifier = Modifier.padding(LocalSpacing.current.medium)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(
                        LocalSpacing.current.small
                    )
                ) {
                    OutlinedTextField(
                        value = viewState.boardName,
                        onValueChange = {
                            onBoardNameChange(it)
                        },
                        label = { Text(text = stringResource(id = R.string.board_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = LocalSpacing.current.medium),
                        onClick = { onCreateBoardButtonAction() },
                    ) {
                        Text(
                            text = stringResource(id = R.string.create_board).uppercase(),
                            style = SignInSignUpButtonText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateBoardScreenPreview() {
    val viewState = CreateBoardViewState("Board name")

    CreateBoardScreen(viewState, {}, {}, {})
}
