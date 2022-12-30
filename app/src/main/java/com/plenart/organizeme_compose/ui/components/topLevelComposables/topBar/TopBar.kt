package com.plenart.organizeme_compose.ui.components.topLevelComposables.topBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigationIcon: @Composable (() -> Unit)? = null) {

    if (navigationIcon != null) {
        CenterAlignedTopAppBar(
            navigationIcon = navigationIcon,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            title = {
                Text(text = "this is a placeholder title")
            },
        )
    } else {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            title = {
                Text(text = "this is a placeholder title")
            }
        )
    }
}

@Composable
fun MenuIcon(onIconAction: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = { onIconAction() }, modifier = modifier) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "Nav drawer icon")
    }
}
