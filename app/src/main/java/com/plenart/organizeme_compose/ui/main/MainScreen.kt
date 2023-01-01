package com.plenart.organizeme_compose.ui.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plenart.organizeme_compose.navigation.NavigationItem
import com.plenart.organizeme_compose.ui.components.topLevelComposables.navigationDrawer.DrawerBody
import com.plenart.organizeme_compose.ui.components.topLevelComposables.navigationDrawer.DrawerHeader
import com.plenart.organizeme_compose.ui.components.topLevelComposables.navigationDrawer.MenuItem
import com.plenart.organizeme_compose.ui.components.topLevelComposables.topBar.MenuIcon
import com.plenart.organizeme_compose.ui.components.topLevelComposables.topBar.TopBar
import com.plenart.organizeme_compose.ui.homeScreen.HomeScreenRoute
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
    val showMenuIcon = navBackStackEntry?.destination?.route == NavigationItem.HomeDestination.route

    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(navigationIcon = {
                if (showMenuIcon) {
                    MenuIcon(
                        onIconAction = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    )
                }
            })
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "sign_out",
                        title = "Sign Out",
                        icon = Icons.Default.ExitToApp,
                        contentDesc = "Sign out action"
                    )
                ), onItemAction = {
                    when (it.id) {
                        "sign_out" -> {
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
                startDestination = NavigationItem.SignUpDestination.route,
                modifier = Modifier.padding(padding)
            ) {
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
