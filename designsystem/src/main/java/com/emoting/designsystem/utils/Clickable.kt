package com.emoting.designsystem.utils

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role

private const val DEFAULT_PRESS_DEPTH = 0.97f

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.clickable(
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    indication: Indication? = null,
    role: Role? = null,
    pressDepth: Float = DEFAULT_PRESS_DEPTH,
    onClick: () -> Unit,
): Modifier {
    val pressed by interactionSource.collectIsPressedAsState()
    val scaleX by animateFloatAsState(
        targetValue = if (pressed) pressDepth
        else 1f,
        label = "",
    )
    val scaleY by animateFloatAsState(
        targetValue = if (pressed) pressDepth
        else 1f,
        label = "",
    )

    return this
        .graphicsLayer {
            this.scaleX = scaleX
            this.scaleY = scaleY
        }
        .clickable(
            onClick = onClick,
            enabled = enabled,
            interactionSource = interactionSource,
            indication = indication,
            role = role,
        )
}
