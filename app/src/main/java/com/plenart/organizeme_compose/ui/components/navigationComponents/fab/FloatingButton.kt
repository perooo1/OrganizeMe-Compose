package com.plenart.organizeme_compose.ui.components.navigationComponents.fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FloatingButton(
    containerColor: Color,
    imageVector: ImageVector,
    contentDescription: String,
    onFloatingButtonAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onFloatingButtonAction,
        containerColor = containerColor,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}
