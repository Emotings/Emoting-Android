package com.emoting.android.feature.loading

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Loading()
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = stringResource(id = R.string.loading),
            style = EmotingTypography.TextMedium,
        )
    }
}

@Composable
fun Loading() {
    val colors = remember {
        mutableStateOf(
            listOf(
                EmotingColors.Gray300,
                EmotingColors.Gray300,
                EmotingColors.Gray200,
                Color(0xFFDADADA),
                Color(0xFFDADADA),
                Color(0xFFE0E0E0),
                Color(0xFFE8E8E8),
                Color(0xFFEDEDED),
            )
        )
    }
    LaunchedEffect(true) {
        while (true) {
            delay(200)
            colors.value = colors.value.rotate(1)
        }
    }
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(12.dp)
    ) {
        val radius: Float = size.minDimension / 2.0f
        val gradationCount = 8
        repeat(gradationCount) { index ->
            val angleInDegree = (index * 360 / gradationCount).toDouble()
            val angleInRadian = Math.toRadians(angleInDegree)
            val length = 20.dp.toPx()
            drawContext.canvas.nativeCanvas.apply {
                val boxSize = 20.dp.toPx()
                val boxRadius = radius - length - boxSize
                val x = boxRadius * cos(angleInRadian) + center.x
                val y = boxRadius * sin(angleInRadian) + center.y + boxSize / 2f
                drawCircle(
                    radius = 20.dp.toPx(),
                    color = colors.value[index],
                    center = Offset(x.toFloat(), y.toFloat())
                )
            }
        }
    }
}
fun <T> List<T>.rotate(rotationDistance: Int): List<T> {
    if (this.isEmpty()) return this
    val realRotation = rotationDistance % this.size
    return this.takeLast(realRotation) + this.dropLast(realRotation)
}
