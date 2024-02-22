package com.emoting.android.feature.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.utils.clickable

@Composable
internal fun LandingScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_emoting_landing),
            contentDescription = "emoting logo",
        )
        Spacer(modifier = Modifier.height(28.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_logo_landing),
            contentDescription = "emoting logo",
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            IconButton(onClick = { /*TODO 카카오 로그인*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_kakao),
                    contentDescription = "kakao",
                )
            }
            IconButton(onClick = { /*TODO 네이버 로그인*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_naver),
                    contentDescription = "kakao",
                )
            }
            IconButton(onClick = { /*TODO 구글 로그인*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "kakao",
                )
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                modifier = Modifier.clickable(onClick = onSignUpClick),
                text = stringResource(id = R.string.sign_up_to_email),
                style = EmotingTypography.TextMedium,
                color = EmotingColors.Gray600,
            )
            Text(
                text = "|",
                style = EmotingTypography.TextMedium,
                color = EmotingColors.Gray600,
            )
            Text(
                modifier = Modifier.clickable(onClick = onSignInClick),
                text = stringResource(id = R.string.sign_in_to_email),
                style = EmotingTypography.TextMedium,
                color = EmotingColors.Gray600,
            )
        }
    }
}
