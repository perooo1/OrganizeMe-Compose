package com.plenart.organizeme_compose.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plenart.organizeme_compose.navigation.NavigationItem
import com.plenart.organizeme_compose.ui.homeScreen.HomeScreenRoute
import com.plenart.organizeme_compose.ui.signUp.SignUpRoute
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = { TopBar() }
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
                        onNavigateToHomeScreen = {
                            navController.navigate(NavigationItem.HomeDestination.route)
                        }
                    )
                }
                composable(NavigationItem.HomeDestination.route) {
                    HomeScreenRoute()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ),
        title = {
            Text(text = "this is a placeholder title")
        }
    )
}
