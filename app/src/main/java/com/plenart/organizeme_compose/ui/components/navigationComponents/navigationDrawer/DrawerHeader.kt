package com.plenart.organizeme_compose.ui.components.navigationComponents.navigationDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.theme.DrawerHeaderUserEmail
import com.plenart.organizeme_compose.ui.theme.DrawerHeaderUserName
import com.plenart.organizeme_compose.ui.theme.LocalSpacing

@Composable
fun DrawerHeader(
    userName: String,
    userEmail: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = LocalSpacing.current.large),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_background),
            contentDescription = stringResource(id = R.string.drawer_header_background_img),
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = userName,
                style = DrawerHeaderUserName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = userEmail,
                style = DrawerHeaderUserEmail,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerHeaderPreview() {
    DrawerHeader("Jozo bubalo", "jozo@bubalo.com")
}
