package com.emoting.android.feature.store

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingButton
import com.emoting.designsystem.ui.button.EmotingIconButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.utils.clickable

private enum class PriceType {
    MIN,
    MAX,
}

@Composable
internal fun StoreFilterDialog(
    onDismissRequest: () -> Unit,
    onSearchClick: (Int, Int) -> Unit,
) {
    val (minPrice, setMinPrice) = remember { mutableFloatStateOf(0f) }
    val (maxPrice, setMaxPrice) = remember { mutableFloatStateOf(10000f) }
    val (priceType, setPriceType) = remember { mutableStateOf(PriceType.MIN) }
    val (valueRange, onValueChange) = when (priceType) {
        PriceType.MIN -> 0f..maxPrice - 1f to setMinPrice
        PriceType.MAX -> minPrice + 1f..10000f to setMaxPrice
    }
    val value = when (priceType) {
        PriceType.MAX -> maxPrice
        PriceType.MIN -> minPrice
    }
    val (minPriceColor, maxPriceColor) = when (priceType) {
        PriceType.MAX -> EmotingColors.Gray200 to EmotingColors.Primary500
        PriceType.MIN -> EmotingColors.Primary500 to EmotingColors.Gray200
    }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(EmotingColors.White)
            .padding(
                horizontal = 12.dp,
                vertical = 16.dp,
            ),
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.filter),
                style = EmotingTypography.TitleSmall,
            )
            Spacer(modifier = Modifier.weight(1f))
            EmotingIconButton(
                onClick = onDismissRequest,
                painter = painterResource(id = com.emoting.android.design_system.R.drawable.ic_close),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.clickable(onClick = { setPriceType(PriceType.MIN) }),
                text = minPrice.toInt().toString(),
                color = minPriceColor,
                style = EmotingTypography.TextSmall,
            )
            Text(
                modifier = Modifier.clickable(onClick = { setPriceType(PriceType.MAX) }),
                text = maxPrice.toInt().toString(),
                color = maxPriceColor,
                style = EmotingTypography.TextSmall,
            )
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            colors = SliderDefaults.colors(
                thumbColor = EmotingColors.Primary500,
                activeTrackColor = EmotingColors.Primary500,
            ),
            valueRange = valueRange,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PriceButton(
                text = stringResource(id = R.string.min_price),
                color = minPriceColor,
                onClick = { setPriceType(PriceType.MIN) },
            )
            Text(
                text = "~",
                color = EmotingColors.Gray100,
            )
            PriceButton(
                text = stringResource(id = R.string.max_price),
                color = maxPriceColor,
                onClick = { setPriceType(PriceType.MAX) },
            )
        }
        EmotingButton(
            text = stringResource(id = R.string.search),
            color = ButtonColor.Primary,
            onClick = {},
        )
    }
}

@Composable
private fun PriceButton(
    text: String,
    color: Color,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = color,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                vertical = 6.dp,
                horizontal = 16.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = color,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StoreFilterDialogPreview() {
    StoreFilterDialog({}, { _, _ -> })
}
