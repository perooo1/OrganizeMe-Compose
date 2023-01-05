package com.plenart.organizeme_compose.ui.boardDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.components.navigationComponents.topBar.TopBar
import com.plenart.organizeme_compose.ui.components.navigationComponents.topBar.TopBarIcon
import com.plenart.organizeme_compose.ui.theme.BoardDetailsHeader
import com.plenart.organizeme_compose.ui.theme.LocalSpacing
import com.plenart.organizeme_compose.ui.theme.SignUpSignInDescription

@Composable
fun BoardDetailsRoute(
    viewModel: BoardDetailsViewModel,
    onTopBarNavigationAction: () -> Unit,
    ) {
    val viewState by viewModel.boardDetailsViewState.collectAsState()

    BoardDetailsScreen(
        viewState = viewState,
        onTopBarNavigationAction = {
            onTopBarNavigationAction()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardDetailsScreen(
    viewState: BoardDetailsViewState,
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(id = R.string.board_name),
                style = BoardDetailsHeader,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    vertical = LocalSpacing.current.medium,
                )
            )
            Text(
                text = viewState.boardName,
                style = SignUpSignInDescription,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    vertical = LocalSpacing.current.medium,
                )
            )
            Text(
                text = stringResource(id = R.string.board_created_by),
                style = BoardDetailsHeader,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    vertical = LocalSpacing.current.medium,
                )
            )
            Text(
                text = viewState.createdBy,
                style = SignUpSignInDescription,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    vertical = LocalSpacing.current.medium,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardDetailsPreview() {
    val viewState = BoardDetailsViewState("jfnejfn", "random", "somebody")

    BoardDetailsScreen(viewState = viewState, {})
}
