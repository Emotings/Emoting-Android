package com.emoting.designsystem.ui.button

import androidx.compose.ui.graphics.Color
import com.emoting.designsystem.ui.theme.EmotingColors

sealed class ButtonColor(
    val defaultOutlineColor: Color,
    val defaultBackgroundColor: Color,
    val defaultTextColor: Color,
    val activeOutlineColor: Color,
    val activeBackgroundColor: Color,
    val activeTextColor: Color,
    val disabledOutlineColor: Color,
    val disabledBackgroundColor: Color,
    val disabledTextColor: Color,
) {
    data object Primary : ButtonColor(
        defaultOutlineColor = EmotingColors.Primary500,
        defaultBackgroundColor = EmotingColors.Primary500,
        defaultTextColor = EmotingColors.White,
        activeOutlineColor = EmotingColors.Primary600,
        activeBackgroundColor = EmotingColors.Primary600,
        activeTextColor = EmotingColors.Gray200,
        disabledOutlineColor = EmotingColors.Gray200,
        disabledBackgroundColor = EmotingColors.Gray200,
        disabledTextColor = EmotingColors.Gray600,
    )

    data object Secondary : ButtonColor(
        defaultOutlineColor = EmotingColors.Primary500,
        defaultBackgroundColor = EmotingColors.White,
        defaultTextColor = EmotingColors.Primary500,
        activeOutlineColor = EmotingColors.Primary600,
        activeBackgroundColor = EmotingColors.Gray100,
        activeTextColor = EmotingColors.Primary600,
        disabledOutlineColor = EmotingColors.Gray300,
        disabledBackgroundColor = EmotingColors.White,
        disabledTextColor = EmotingColors.Gray600,
    )
}
