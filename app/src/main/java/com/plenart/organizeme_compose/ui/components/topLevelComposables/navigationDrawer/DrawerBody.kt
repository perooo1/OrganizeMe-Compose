package com.plenart.organizeme_compose.ui.components.topLevelComposables.navigationDrawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.plenart.organizeme_compose.ui.theme.LocalSpacing

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    onItemAction: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemAction(item)
                    }
                    .padding(LocalSpacing.current.medium)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.contentDesc)
                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                Text(
                    text = item.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
