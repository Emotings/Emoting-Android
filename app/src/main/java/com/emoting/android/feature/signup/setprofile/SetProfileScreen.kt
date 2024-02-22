package com.emoting.android.feature.signup.setprofile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.ui.topbar.EmotingTopBar
import com.emoting.designsystem.utils.clickable

@Composable
internal fun SetProfileScreen(
    onBackPressed: () -> Unit,
    onNextClick: () -> Unit,
) {
    var uri: Uri? by remember { mutableStateOf(null) }
    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri = it },
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EmotingTopBar(
            onBackPressed = onBackPressed,
            title = stringResource(id = R.string.sign_up),
        )
        Text(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 40.dp,
            ),
            text = stringResource(id = R.string.set_profile),
            style = EmotingTypography.TitleMedium,
        )
        ProfileImage(
            uri = uri,
            onClick = {
                val mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                val request = PickVisualMediaRequest(mediaType)
                activityResultLauncher.launch(request)
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        EmotingButton(
            modifier = Modifier.offset(y = 20.dp),
            text = stringResource(id = R.string.later),
            color = ButtonColor.Secondary,
            onClick = onNextClick,
        )
        EmotingButton(
            text = stringResource(id = R.string.next),
            color = ButtonColor.Primary,
            onClick = onNextClick,
        )
    }
}

@Composable
private fun ProfileImage(
    uri: Uri?,
    onClick: () -> Unit,
) {
    Box(modifier = Modifier.clickable(onClick = onClick)) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(EmotingColors.Gray200),
            contentAlignment = Alignment.Center,
        ) {
            if (uri == null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gallery),
                    contentDescription = "gallery",
                )
            } else {
                // TODO AsyncImage 사용하기
            }
        }
        if (uri == null) {
            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(EmotingColors.Gray100)
                    .padding(8.dp),
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add",
                tint = EmotingColors.Gray500,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSetProfileScreen() {
    SetProfileScreen(onBackPressed = { /*TODO*/ }) {

    }
}
