package com.emoting.android.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.android.ui.ChatContent
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingFloatingButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography

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
            ChatContent(
                profile = it.profile,
                name = it.name,
                boolean = it.hasNewMessage,
                trueMessage = stringResource(id = R.string.not_read_message),
                falseMessage = stringResource(id = R.string.read_all_message),
            )
        }
    }
}
