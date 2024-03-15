package com.emoting.designsystem.ui.floatingbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.utils.clickable

@Composable
fun BoxScope.EmotingFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .align(Alignment.BottomEnd)
            .clickable(onClick = onClick)
            .padding(20.dp)
            .shadow(
                elevation = 1.dp,
                shape = CircleShape,
            )
            .clip(CircleShape)
            .background(EmotingColors.Primary500)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
        content = content,
    )
}
