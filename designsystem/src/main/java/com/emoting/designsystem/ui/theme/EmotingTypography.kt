package com.emoting.designsystem.ui.theme

import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.emoting.android.design_system.R

private val PretendardFontFamily = FontFamily(
    listOf(
        Font(R.font.pretendard_regular),
        Font(R.font.pretendard_semi_bold),
    )
)

private val platFormTextStyle = PlatformTextStyle(
    includeFontPadding = false,
)

object EmotingTypography {
    val TitleLarge = TextStyle(
        fontFamily = PretendardFontFamily,
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = TextUnit(
            value = 1.2f,
            type = TextUnitType.Unspecified,
        ),
        platformStyle = platFormTextStyle,
        color = EmotingColors.Black,
    )

    val TitleMedium = TextStyle(
        fontFamily = PretendardFontFamily,
        fontSize = 26.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = TextUnit(
            value = 1.2f,
            type = TextUnitType.Unspecified,
        ),
        platformStyle = platFormTextStyle,
        color = EmotingColors.Black,
    )

    val TitleSmall = TextStyle(
        fontFamily = PretendardFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = TextUnit(
            value = 1.3f,
            type = TextUnitType.Unspecified,
        ),
        platformStyle = platFormTextStyle,
        color = EmotingColors.Black,
    )

    val TextLarge = TextStyle(
        fontFamily = PretendardFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = TextUnit(
            value = 1.4f,
            type = TextUnitType.Unspecified,
        ),
        platformStyle = platFormTextStyle,
        color = EmotingColors.Black,
    )

    val TextMedium = TextStyle(
        fontFamily = PretendardFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = TextUnit(
            value = 1.4f,
            type = TextUnitType.Unspecified,
        ),
        platformStyle = platFormTextStyle,
        color = EmotingColors.Black,
    )

    val TextSmall = TextStyle(
        fontFamily = PretendardFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = TextUnit(
            value = 1.4f,
            type = TextUnitType.Unspecified,
        ),
        platformStyle = platFormTextStyle,
        color = EmotingColors.Black,
    )

    val Caption = TextStyle(
        fontFamily = PretendardFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = TextUnit(
            value = 1.4f,
            type = TextUnitType.Unspecified,
        ),
        platformStyle = platFormTextStyle,
        color = EmotingColors.Black,
    )
}
