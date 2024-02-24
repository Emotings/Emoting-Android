package com.emoting.android.feature.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emoting.android.R
import com.emoting.designsystem.ui.button.EmotingIconButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.ui.topbar.EmotingTopBar
import com.emoting.designsystem.utils.clickable

@Composable
internal fun ChattingScreen(
    onBackPressed: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EmotingColors.Gray50),
    ) {
        EmotingTopBar(
            title = "name",
            onBackPressed = onBackPressed,
        ) {
            EmotingIconButton(
                onClick = {},
                painter = painterResource(id = R.drawable.ic_search),
            )
        }
        // TODO LazyColumn 사용
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            MyChatContent(time = "오후 2:35")
            OthersChatContent(
                profileUrl = "",
                name = "정승훈",
                time = "오후 2:36",
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Input()
    }
}

@Composable
private fun MyChatContent(time: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = time,
            color = EmotingColors.Gray600,
            style = EmotingTypography.Caption,
        )
        Spacer(modifier = Modifier.width(8.dp))
        ChatEmoji(
            isMine = true,
            emojis = emptyList(),
        )
    }
}

@Composable
private fun OthersChatContent(
    profileUrl: String,
    name: String,
    time: String,
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape),
            model = profileUrl,
            contentDescription = "profile",
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = name,
                style = EmotingTypography.TextSmall,
            )
            ChatEmoji(
                isMine = false,
                emojis = emptyList(),
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = time,
            color = EmotingColors.Gray600,
            style = EmotingTypography.Caption,
        )
    }
}

@Composable
private fun ChatEmoji(
    isMine: Boolean,
    emojis: List<String>,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp,
                    topStart = if (isMine) 12.dp else 0.dp,
                    topEnd = if (isMine) 0.dp else 12.dp,
                )
            )
            .background(
                color = if (isMine) EmotingColors.Primary500
                else EmotingColors.White,
            )
            .padding(
                horizontal = 14.dp,
                vertical = 10.dp,
            )
    ) {
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(id = R.drawable.ic_test),
            contentDescription = "image",
        )
    }
}

@Composable
private fun Input() {
    var showEmojiKeyboard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(EmotingColors.White)
            .padding(
                vertical = 8.dp,
                horizontal = 20.dp,
            )
            .clickable(
                onClick = { showEmojiKeyboard = !showEmojiKeyboard },
                pressDepth = 0.99f,
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(EmotingColors.Gray50)
                .padding(
                    vertical = 12.dp,
                    horizontal = 20.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.select_emoji),
                color = EmotingColors.Gray400,
                style = EmotingTypography.TextSmall,
            )
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_gif),
                contentDescription = "gif",
            )
        }
    }
}
