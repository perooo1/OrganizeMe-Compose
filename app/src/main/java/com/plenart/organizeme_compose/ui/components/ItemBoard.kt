package com.plenart.organizeme_compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.plenart.organizeme_compose.R
import com.plenart.organizeme_compose.ui.theme.IntroDescription
import com.plenart.organizeme_compose.ui.theme.LocalSpacing

data class ItemBoardViewState(
    val boardId: String = "",
    val boardName: String = "",
    val createdBy: String = ""
)

@Composable
fun ItemBoard(
    viewState: ItemBoardViewState,
    onBoardAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .clickable { onBoardAction() }
        .fillMaxWidth()
        .padding(
            horizontal = LocalSpacing.current.medium,
            vertical = LocalSpacing.current.small
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.board_img_placeholder),
                modifier = Modifier
                    .padding(LocalSpacing.current.medium)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = viewState.boardName,
                    style = IntroDescription,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        LocalSpacing.current.medium
                    )
                )
                Text(
                    text = viewState.boardId,
                    style = IntroDescription,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        LocalSpacing.current.medium
                    )
                )
                Text(
                    text = viewState.createdBy,
                    style = IntroDescription,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        LocalSpacing.current.medium
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemBoardPreview() {
    val viewState = ItemBoardViewState("boardId", "hehe", "jozo")

    ItemBoard(viewState = viewState, onBoardAction = { })
}
