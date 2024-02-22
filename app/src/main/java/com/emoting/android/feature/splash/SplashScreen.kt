package com.emoting.android.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.emoting.android.R
import com.emoting.designsystem.ui.theme.EmotingColors
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen(navigateToLanding: () -> Unit) {
    LaunchedEffect(Unit){
        // TODO 자동 로그인 로직으로 변경
        delay(2000L)
        navigateToLanding()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(EmotingColors.Primary500),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_emoting),
            contentDescription = "splash image",
        )
    }
}
