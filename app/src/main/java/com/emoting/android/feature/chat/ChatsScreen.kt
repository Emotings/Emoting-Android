package com.emoting.android.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emoting.android.R
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingFloatingButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.utils.clickable

private data class Chat(
    val profile: String,
    val name: String,
    val hasNewMessage: Boolean,
)

@Composable
internal fun ChatsScreen() {
    val chats = remember { mutableStateListOf<Chat>() }
    chats.add(
        Chat(
            profile = "",
            name = "홍길동",
            hasNewMessage = false,
        ),
    )
    chats.add(
        Chat(
            profile = "",
            name = "홍길동",
            hasNewMessage = true,
        ),
    )

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(EmotingColors.White)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.chats),
                style = EmotingTypography.TitleSmall,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Chats(
                chats = chats,
            )
        }
        EmotingFloatingButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.random_chatting),
            color = ButtonColor.Primary,
            onClick = {},
        )
    }
}

@Composable
private fun Chats(
    chats: SnapshotStateList<Chat>,
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        items(chats) {
            ChatContent(chat = it)
        }
    }
}

@Composable
private fun ChatContent(
    chat: Chat,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {},
                pressDepth = 0.98f,
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            model = chat.profile,
            contentDescription = "chat profile",
        )
        Column {
            Text(
                text = chat.name,
                style = EmotingTypography.TextMedium,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(
                            color = if (chat.hasNewMessage) EmotingColors.Primary500
                            else EmotingColors.Gray300,
                        )
                )
                Text(
                    text = stringResource(
                        id = if (chat.hasNewMessage) R.string.not_read_message
                        else R.string.read_all_message,
                    ),
                    style = EmotingTypography.TextSmall,
                    color = if (chat.hasNewMessage) EmotingColors.Primary500
                    else EmotingColors.Gray300,
                )
            }
        }
    }
}
