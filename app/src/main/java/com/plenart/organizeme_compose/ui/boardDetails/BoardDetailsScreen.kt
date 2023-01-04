package com.plenart.organizeme_compose.ui.boardDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.ui.theme.LocalSpacing
import com.plenart.organizeme_compose.ui.theme.SignUpSignInDescription

@Composable
fun BoardDetailsRoute(viewModel: BoardDetailsViewModel) {
    val viewState by viewModel.boardDetailsViewState.collectAsState()

    BoardDetailsScreen(viewState = viewState)
}

@Composable
fun BoardDetailsScreen(
    viewState: BoardDetailsViewState,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = viewState.boardName,
            style = SignUpSignInDescription,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(
                vertical = LocalSpacing.current.medium,
            )
        )
        Text(
            text = viewState.boardId,
            style = SignUpSignInDescription,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(
                vertical = LocalSpacing.current.medium,
            )
        )
        Text(
            text = viewState.createdBy,
            style = SignUpSignInDescription,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(
                vertical = LocalSpacing.current.medium,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BoardDetailsPreview() {
    val viewState = BoardDetailsViewState("jfnejfn", "random", "somebody")

    BoardDetailsScreen(viewState = viewState)
}
