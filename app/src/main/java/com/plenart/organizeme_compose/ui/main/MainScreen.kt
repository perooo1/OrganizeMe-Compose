package com.plenart.organizeme_compose.ui.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.navigation.BOARD_ID_KEY
import com.plenart.organizeme_compose.navigation.BoardDetailsDestination
import com.plenart.organizeme_compose.navigation.NavigationItem
import com.plenart.organizeme_compose.ui.boardDetails.BoardDetailsRoute
import com.plenart.organizeme_compose.ui.boardDetails.BoardDetailsViewModel
import com.plenart.organizeme_compose.ui.components.navigationComponents.fab.FloatingButton
import com.plenart.organizeme_compose.ui.homeScreen.HomeScreenRoute
import com.plenart.organizeme_compose.ui.intro.IntroScreenRoute
import com.plenart.organizeme_compose.ui.signIn.SignInRoute
import com.plenart.organizeme_compose.ui.signUp.SignUpRoute
import com.plenart.organizeme_compose.ui.theme.Teal200
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val isCurrentDestinationHome =
        navBackStackEntry?.destination?.route == NavigationItem.HomeDestination.route

    Scaffold(
        floatingActionButton = {
            if (isCurrentDestinationHome) {
                FloatingButton(
                    containerColor = Teal200,
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_board_fab),
                    onFloatingButtonAction = { Log.i("MainScreen", "FAB clicked") }
                )
            }
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.IntroDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.IntroDestination.route) {
                    IntroScreenRoute(
                        onSignUpButtonAction = {
                            navController.navigate(NavigationItem.SignUpDestination.route)
                        },
                        onSignInButtonAction = {
                            navController.navigate(NavigationItem.SignInDestination.route)
                        }
                    )
                }
                composable(NavigationItem.SignUpDestination.route) {
                    SignUpRoute(
                        viewModel = getViewModel(),
                        onNavigateToSignInScreen = {
                            navController.navigate(NavigationItem.SignInDestination.route) {
                                popUpTo(NavigationItem.SignUpDestination.route) { inclusive = true }
                            }
                        }
                    )
                }
                composable(NavigationItem.SignInDestination.route) {
                    SignInRoute(
                        viewModel = getViewModel(),
                        onNavigateToHomeScreen = {
                            navController.navigate(NavigationItem.HomeDestination.route) {
                                popUpTo(NavigationItem.SignInDestination.route) { inclusive = true }
                                popUpTo(NavigationItem.IntroDestination.route) { inclusive = true }
                            }
                        }
                    )
                }
                composable(NavigationItem.HomeDestination.route) {
                    HomeScreenRoute(
                        homeScreenViewModel = getViewModel(),
                        onNavigateToBoardDetails = { boardId ->
                            navController.navigate(
                                BoardDetailsDestination.createNavigationRoute(boardId)
                            )
                        },
                        onNavigateToIntroScreen = {
                            navController.navigate(NavigationItem.IntroDestination.route){
                                popUpTo(NavigationItem.HomeDestination.route) { inclusive = true }
                                popUpTo(BoardDetailsDestination.route) { inclusive = true }
                            }
                        }
                    )
                }
                composable(
                    route = BoardDetailsDestination.route,
                    arguments = listOf(navArgument(BOARD_ID_KEY) { type = NavType.StringType })
                ) {
                    val boardId = it.arguments?.getString(BOARD_ID_KEY)
                    val boardDetailsViewModel =
                        getViewModel<BoardDetailsViewModel>(parameters = { parametersOf(boardId) })

                    BoardDetailsRoute(boardDetailsViewModel)
                }
            }
        }
    }
}
