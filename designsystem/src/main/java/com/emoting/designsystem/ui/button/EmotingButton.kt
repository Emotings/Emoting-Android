package com.emoting.designsystem.ui.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.utils.clickable

@Composable
private fun BasicButton(
    modifier: Modifier,
    text: String,
    shape: RoundedCornerShape,
    enabled: Boolean,
    outlineColor: Color,
    backgroundColor: Color,
    textColor: Color,
    style: TextStyle,
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .clickable(
                interactionSource = interactionSource,
                onClick = onClick,
                enabled = enabled,
            )
            .clip(shape)
            .border(
                width = 1.dp,
                color = outlineColor,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = backgroundColor)
            .padding(
                vertical = 12.dp,
                horizontal = 22.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = style,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun ColoredButton(
    modifier: Modifier,
    text: String,
    color: ButtonColor,
    shape: RoundedCornerShape,
    enabled: Boolean,
    style: TextStyle,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val outlineColor by animateColorAsState(
        targetValue = if (!enabled) color.disabledOutlineColor
        else if (pressed) color.activeOutlineColor
        else color.defaultOutlineColor,
        label = "",
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (!enabled) color.disabledBackgroundColor
        else if (pressed) color.activeBackgroundColor
        else color.defaultBackgroundColor,
        label = "",
    )
    val textColor by animateColorAsState(
        targetValue = if (!enabled) color.disabledTextColor
        else if (pressed) color.activeTextColor
        else color.defaultTextColor,
        label = "",
    )

    BasicButton(
        modifier = modifier,
        text = text,
        enabled = enabled,
        shape = shape,
        outlineColor = outlineColor,
        backgroundColor = backgroundColor,
        textColor = textColor,
        style = style,
        interactionSource = interactionSource,
        onClick = onClick,
    )
}

@Composable
fun EmotingButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColor,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    ColoredButton(
        modifier = modifier.fillMaxWidth(),
        text = text,
        color = color,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        style = EmotingTypography.TextLarge,
        onClick = onClick,
    )
}

@Composable
fun EmotingFloatingButton(
    modifier: Modifier = Modifier,
    text: String,
    color: ButtonColor,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    ColoredButton(
        modifier = modifier,
        text = text,
        color = color,
        enabled = enabled,
        shape = RoundedCornerShape(20.dp),
        style = EmotingTypography.TextMedium,
        onClick = onClick,
    )
}
