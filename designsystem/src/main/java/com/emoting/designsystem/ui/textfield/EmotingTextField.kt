package com.emoting.designsystem.ui.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.emoting.android.design_system.R
import com.emoting.designsystem.ui.button.EmotingIconButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography

@Composable
fun EmotingBoxTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    title: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    background: Color = EmotingColors.Gray50,
    onValueChange: (String) -> Unit,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    val hintVisible by animateFloatAsState(
        targetValue = if (value.isEmpty()) 1f
        else 0f,
        label = "",
    )
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        title?.let {
            Text(
                text = it,
                color = EmotingColors.Black,
                style = EmotingTypography.TextMedium,
            )
        }
        BasicTextField(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(background)
                .padding(16.dp),
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
        ) { innerTextField ->
            Box(contentAlignment = Alignment.CenterStart) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    innerTextField()
                    actions?.invoke(this)
                }
                Text(
                    modifier = Modifier.alpha(hintVisible),
                    text = hint,
                    style = EmotingTypography.TextMedium,
                    color = EmotingColors.Gray300,
                )
            }
        }
    }
}

@Composable
private fun EmotingBasicUnderLineTextField(
    modifier: Modifier,
    value: String,
    hint: String,
    onFocusChange: (Boolean) -> Unit,
    enabled: Boolean,
    isError: Boolean,
    showVisibleIcon: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    description: String?,
    underLineColor: Color,
    hintColor: Color,
    singleLine: Boolean,
    onValueChange: (String) -> Unit,
    content: (@Composable RowScope.() -> Unit)?,
) {
    var visible by remember { mutableStateOf(!showVisibleIcon) }
    val offsetY by animateDpAsState(
        targetValue = if (value.isNotEmpty()) (-30).dp
        else 0.dp,
        label = "",
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Box(contentAlignment = Alignment.BottomStart) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = modifier
                        .weight(1f)
                        .onFocusChanged {
                            onFocusChange(it.isFocused)
                        },
                    decorationBox = { innerTextField ->
                        innerTextField()
                    },
                    textStyle = EmotingTypography.TextMedium,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction,
                    ),
                    enabled = enabled,
                    singleLine = singleLine,
                    visualTransformation = if (visible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.width(4.dp))
                content?.invoke(this)
                if (showVisibleIcon) {
                    EmotingIconButton(
                        onClick = { visible = !visible },
                        painter = painterResource(
                            id = if (visible) R.drawable.ic_eye_on
                            else R.drawable.ic_eye_off,
                        ),
                        contentDescription = "visible",
                    )
                }
            }
            Text(
                modifier = Modifier.offset(
                    y = offsetY,
                ),
                text = hint,
                color = hintColor,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = underLineColor,
        )
        if (isError) {
            description?.run {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    color = EmotingColors.Error500,
                )
            }
        }
    }
}

@Composable
private fun ColoredTextField(
    modifier: Modifier,
    value: String,
    hint: String,
    enabled: Boolean,
    isError: Boolean,
    showVisibleIcon: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    description: String?,
    singleLine: Boolean,
    onValueChange: (String) -> Unit,
    content: (@Composable RowScope.() -> Unit)?,
) {
    var focused by remember { mutableStateOf(false) }
    val underLineColor by animateColorAsState(
        targetValue = if (!enabled) EmotingColors.Gray600
        else if (isError) EmotingColors.Error500
        else if (focused) EmotingColors.Primary500
        else EmotingColors.Gray600,
        label = "",
    )
    val hintColor by animateColorAsState(
        targetValue = if (isError && value.isNotEmpty()) EmotingColors.Error500
        else EmotingColors.Gray600,
        label = "",
    )

    EmotingBasicUnderLineTextField(
        modifier = modifier,
        value = value,
        hint = hint,
        onFocusChange = { focused = it },
        enabled = enabled,
        isError = isError,
        showVisibleIcon = showVisibleIcon,
        keyboardType = keyboardType,
        imeAction = imeAction,
        description = description,
        underLineColor = underLineColor,
        hintColor = hintColor,
        singleLine = singleLine,
        onValueChange = onValueChange,
        content = content,
    )
}

@Composable
fun EmotingTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isError: Boolean = false,
    showVisibleIcon: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    description: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    content: (@Composable RowScope.() -> Unit)? = null,
) {
    ColoredTextField(
        modifier = modifier,
        value = value,
        hint = hint,
        enabled = enabled,
        isError = isError,
        showVisibleIcon = showVisibleIcon,
        keyboardType = keyboardType,
        imeAction = imeAction,
        description = description,
        onValueChange = onValueChange,
        singleLine = singleLine,
        content = content,
    )
}
