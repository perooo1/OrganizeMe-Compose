package com.plenart.organizeme_compose.ui.components.topLevelComposables.navigationDrawer

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val idInResourceFile: Int,
    val title: String,
    val icon: ImageVector,
    val contentDesc: String
)
