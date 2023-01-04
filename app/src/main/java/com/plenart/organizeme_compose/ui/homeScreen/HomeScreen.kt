package com.plenart.organizeme_compose.ui.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.components.ItemBoard
import com.plenart.organizeme_compose.ui.components.ItemBoardViewState

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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val viewState = HomeScreenViewState(emptyList())
    HomeScreen(viewState, "this is homescreen placeholder txt", {}, {})
}
