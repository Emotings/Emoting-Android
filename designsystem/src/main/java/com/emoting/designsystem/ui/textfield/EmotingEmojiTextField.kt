package com.emoting.designsystem.ui.textfield

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.utils.clickable
import kotlinx.coroutines.delay

@Composable
fun EmotingEmojiTextField(
    modifier: Modifier = Modifier,
    emojis: SnapshotStateList<String>,
    hint: String,
    onClick: () -> Unit,
    hasFocus: Boolean = false,
    shape: Shape = RoundedCornerShape(20.dp),
    background: Color = EmotingColors.Gray100,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    val hintVisible by animateFloatAsState(
        targetValue = if (emojis.isEmpty()) 1f
        else 0f,
        label = "",
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(background)
            .clickable(
                onClick = onClick,
                indication = null,
                pressDepth = 1f,
            )
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp,
            ),
    ) {
        Box(contentAlignment = Alignment.CenterStart) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    emojis.forEach {
                        AsyncImage(
                            modifier = Modifier.size(24.dp),
                            model = it,
                            contentDescription = "emoji"
                        )
                    }
                    Cursor(showCursor = hasFocus)
                }
                actions?.invoke(this)
            }
            Text(
                modifier = Modifier.alpha(hintVisible),
                text = hint,
                color = Color(0xFFB4B4B7),
                style = EmotingTypography.TextMedium,
            )
        }
    }
}

@Composable
private fun Cursor(showCursor: Boolean) {
    var show by remember { mutableStateOf(false) }

    LaunchedEffect(showCursor) {
        while (showCursor) {
            delay(500)
            show = !show
        }
    }

    if (show) {
        Box(
            modifier = Modifier
                .size(
                    width = 1.dp,
                    height = 20.dp,
                )
                .background(EmotingColors.Black),
        )
    }
}
