package com.plenart.organizeme_compose.ui.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.navigation.NavigationItem
import com.plenart.organizeme_compose.ui.components.topLevelComposables.navigationDrawer.DrawerBody
import com.plenart.organizeme_compose.ui.components.topLevelComposables.navigationDrawer.DrawerHeader
import com.plenart.organizeme_compose.ui.components.topLevelComposables.navigationDrawer.MenuItem
import com.plenart.organizeme_compose.ui.components.topLevelComposables.topBar.TopBar
import com.plenart.organizeme_compose.ui.components.topLevelComposables.topBar.TopBarIcon
import com.plenart.organizeme_compose.ui.homeScreen.HomeScreenRoute
import com.plenart.organizeme_compose.ui.intro.IntroScreenRoute
import com.plenart.organizeme_compose.ui.signIn.SignInRoute
import com.plenart.organizeme_compose.ui.signUp.SignUpRoute
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val isCurrentDestinationHome =
        navBackStackEntry?.destination?.route == NavigationItem.HomeDestination.route

    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (isCurrentDestinationHome) {
                TopBar(navigationIcon = {
                    TopBarIcon(
                        onIconAction = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        },
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(id = R.string.top_bar_menu_icon)
                    )
                })
            }
        },
        drawerContent = {
            DrawerHeader()
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
                            Log.i("MainScreen", "Sign out button in drawer clicked")
                        }
                    }
                }
            )
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
                        signUpViewModel = getViewModel(),
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
                    HomeScreenRoute(homeScreenViewModel = getViewModel())
                }
            }
        }
    }
}
