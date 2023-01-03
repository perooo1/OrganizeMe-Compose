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
import com.plenart.organizeme_compose.model.Board

@Composable
fun HomeScreenRoute(homeScreenViewModel: HomeScreenViewModel) {
    homeScreenViewModel.getUserInfo()

    val userDataState = homeScreenViewModel.userData.collectAsState()
    val boards by homeScreenViewModel.userBoards.collectAsState()

    if (userDataState.value != null) {
        HomeScreen(
            userName = userDataState.value!!.name,
            boards = boards,
            onButtonAction = { homeScreenViewModel.createBoard() })
    } else {
        HomeScreen(
            userName = "Trenutno null",
            boards = boards,
            onButtonAction = {}
        )
    }
}

@Composable
fun HomeScreen(
    userName: String,
    boards: List<Board>,
    onButtonAction: () -> Unit,
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
        LazyColumn() {
            items(
                items = boards,
                key = { board ->
                    board.documentID
                }
            ) { board ->
                Text(text = board.name)
                Text(text = board.documentID)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen("this is homescreen placeholder txt", emptyList(), {})
}
