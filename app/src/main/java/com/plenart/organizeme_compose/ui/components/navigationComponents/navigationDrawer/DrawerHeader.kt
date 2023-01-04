package com.plenart.organizeme_compose.ui.components.navigationComponents.navigationDrawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.plenart.organizeme_compose.ui.theme.LocalSpacing

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = LocalSpacing.current.extraLarge),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}
