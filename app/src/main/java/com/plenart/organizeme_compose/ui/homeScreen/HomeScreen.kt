package com.plenart.organizeme_compose.ui.homeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.components.ItemBoard
import com.plenart.organizeme_compose.ui.components.ItemBoardViewState
import com.plenart.organizeme_compose.ui.components.navigationComponents.navigationDrawer.DrawerBody
import com.plenart.organizeme_compose.ui.components.navigationComponents.navigationDrawer.DrawerHeader
import com.plenart.organizeme_compose.ui.components.navigationComponents.navigationDrawer.MenuItem
import com.plenart.organizeme_compose.ui.components.navigationComponents.topBar.TopBar
import com.plenart.organizeme_compose.ui.components.navigationComponents.topBar.TopBarIcon
import com.plenart.organizeme_compose.ui.theme.IntroDescription
import com.plenart.organizeme_compose.ui.theme.IntroHeroText
import com.plenart.organizeme_compose.ui.theme.LocalSpacing
import kotlinx.coroutines.launch

@Composable
fun HomeScreenRoute(
    homeScreenViewModel: HomeScreenViewModel,
    onNavigateToBoardDetails: (String) -> Unit,
    onNavigateToIntroScreen: () -> Unit,
) {
    homeScreenViewModel.getUserInfo()

    val userDataState = homeScreenViewModel.userData.collectAsState()
    val viewState by homeScreenViewModel.homeScreenViewState.collectAsState()

    if (userDataState.value != null) {
        HomeScreen(
            viewState = viewState,
            userName = userDataState.value!!.name,
            userEmail = userDataState.value!!.email,
            onButtonAction = { homeScreenViewModel.createBoard() },
            onBoardAction = { onNavigateToBoardDetails(it) },
            onSignOutButtonAction = {
                homeScreenViewModel.signOut()
                onNavigateToIntroScreen()
            }
        )
    } else {
        HomeScreen(
            viewState = viewState,
            userName = "Trenutno null",
            userEmail = "Trenutno null",
            onButtonAction = {},
            onBoardAction = { onNavigateToBoardDetails(it) },
            onSignOutButtonAction = {
                homeScreenViewModel.signOut()
                onNavigateToIntroScreen()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewState: HomeScreenViewState,
    userName: String,
    userEmail: String,
    onButtonAction: () -> Unit,
    onBoardAction: (String) -> Unit,
    onSignOutButtonAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader(userName, userEmail)
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = stringResource(id = R.string.menu_item_sign_out_id),
                            idInResourceFile = R.string.menu_item_sign_out_id,
                            title = stringResource(id = R.string.sign_out),
                            icon = Icons.Default.ExitToApp,
                            contentDesc = stringResource(id = R.string.sign_out)
                        )
                    ), onItemAction = {
                        when (it.idInResourceFile) {
                            R.string.menu_item_sign_out_id -> {
                                onSignOutButtonAction()
                            }
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(navigationIcon = {
                    TopBarIcon(
                        onIconAction = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        imageVector = Icons.Default.Menu,
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
                if (viewState.boards.isEmpty()) {
                    NoBoardsAssigned()
                } else {
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
    HomeScreen(
        viewState = viewState,
        userName = "username",
        userEmail = "email",
        onButtonAction = {},
        onSignOutButtonAction = {},
        onBoardAction = {}
    )
}
