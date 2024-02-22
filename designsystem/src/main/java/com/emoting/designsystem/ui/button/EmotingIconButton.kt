package com.emoting.designsystem.ui.button

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.utils.clickable

@Composable
fun EmotingIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String = "icon button",
    tint: Color = EmotingColors.Gray600,
) {
    Icon(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                indication = rememberRipple(),
            ),
        painter = painter,
        contentDescription = contentDescription,
        tint = tint,
    )
}
