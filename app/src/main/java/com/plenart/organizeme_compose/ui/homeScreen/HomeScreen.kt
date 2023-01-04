package com.plenart.organizeme_compose.ui.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.components.ItemBoard
import com.plenart.organizeme_compose.ui.components.ItemBoardViewState
import com.plenart.organizeme_compose.ui.theme.IntroDescription
import com.plenart.organizeme_compose.ui.theme.IntroHeroText
import com.plenart.organizeme_compose.ui.theme.LocalSpacing

@Composable
fun HomeScreenRoute(
    homeScreenViewModel: HomeScreenViewModel,
    onNavigateToBoardDetails: (String) -> Unit
) {
    homeScreenViewModel.getUserInfo()

    val userDataState = homeScreenViewModel.userData.collectAsState()
    val viewState by homeScreenViewModel.homeScreenViewState.collectAsState()

    if (userDataState.value != null) {
        HomeScreen(
            viewState = viewState,
            userName = userDataState.value!!.name,
            onButtonAction = { homeScreenViewModel.createBoard() },
            onBoardAction = { onNavigateToBoardDetails(it) }
        )
    } else {
        HomeScreen(
            viewState = viewState,
            userName = "Trenutno null",
            onButtonAction = {},
            onBoardAction = { onNavigateToBoardDetails(it) }
        )
    }
}

@Composable
fun HomeScreen(
    viewState: HomeScreenViewState,
    userName: String,
    onButtonAction: () -> Unit,
    onBoardAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = userName)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "img"
        )
        Button(onClick = { onButtonAction() }) {
            Text(text = "create board")
        }
        if (viewState.boards.isEmpty()) {
            NoBoardsAssigned()
        }
        else{
            LazyColumn {
                items(
                    items = viewState.boards,
                    key = { board ->
                        board.boardId
                    }
                ) { board ->
                    ItemBoard(
                        viewState = ItemBoardViewState(
                            boardId = board.boardId,
                            boardName = board.boardName,
                            createdBy = board.createdBy
                        ),
                        onBoardAction = { onBoardAction(board.boardId) }
                    )
                }
            }
        }
    }
}

@Composable
fun NoBoardsAssigned(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.checklist_purple_transparent),
        contentDescription = stringResource(id = R.string.no_boards_available),
        modifier = modifier.padding(LocalSpacing.current.medium)
    )
    Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
    Text(
        text = stringResource(id = R.string.no_boards_available),
        style = IntroHeroText,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(
            vertical = LocalSpacing.current.small
        )
    )
    Text(
        text = stringResource(id = R.string.create_board_description),
        style = IntroDescription,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val viewState = HomeScreenViewState(emptyList())
    HomeScreen(viewState, "this is homescreen placeholder txt", {}, {})
}
