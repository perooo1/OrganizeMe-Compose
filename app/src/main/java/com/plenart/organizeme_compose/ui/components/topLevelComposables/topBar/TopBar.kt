package com.plenart.organizeme_compose.ui.components.topLevelComposables.topBar

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.plenart.organizeme_compose.R

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
                Text(text = stringResource(id = R.string.app_name))
            },
        )
    } else {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
        )
    }
}

@Composable
fun TopBarIcon(
    onIconAction: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = { onIconAction() }, modifier = modifier) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}



