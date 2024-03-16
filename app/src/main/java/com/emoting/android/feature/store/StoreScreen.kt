package com.emoting.android.feature.store

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.emoting.android.R
import com.emoting.android.component.StoreItem
import com.emoting.designsystem.ui.button.EmotingIconButton
import com.emoting.designsystem.ui.floatingbutton.EmotingFloatingActionButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography

@Composable
internal fun StoreScreen() {
    val (showFilterDialog, setShowFilterDialog) = remember { mutableStateOf(false) }

    if (showFilterDialog) {
        Dialog(onDismissRequest = { setShowFilterDialog(false) }) {
            StoreFilterDialog(
                onDismissRequest = { setShowFilterDialog(false) },
                onSearchClick = { minPrice, maxPrice -> },
            )
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            StoreTopBar(onClick = { setShowFilterDialog(true) })
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.point),
                    style = EmotingTypography.TextMedium,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "1,900",
                    style = EmotingTypography.TextMedium,
                    color = EmotingColors.Primary500,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.charge),
                    color = EmotingColors.Gray300,
                    textDecoration = TextDecoration.Underline
                )
            }
            StoreItem(
                emojiUrl = "",
                title = "title",
                point = "102",
                onClick = {},
            )
        }
        EmotingFloatingActionButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add friend",
                tint = EmotingColors.White,
            )
        }
    }
}

@Composable
private fun StoreTopBar(
    onClick: () -> Unit,
) {
    Row {
        Text(
            text = stringResource(id = R.string.emoji_store),
            style = EmotingTypography.TitleSmall,
        )
        Spacer(modifier = Modifier.weight(1f))
        EmotingIconButton(
            onClick = onClick,
            painter = painterResource(id = R.drawable.ic_filter),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StoreItemPreview() {
    StoreScreen()
}
