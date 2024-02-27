package com.emoting.android.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emoting.android.R
import com.emoting.designsystem.ui.button.EmotingIconButton
import com.emoting.designsystem.ui.textfield.EmotingEmojiTextField
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography

@Composable
internal fun SearchChattingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EmotingColors.Gray50)
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        EmotingEmojiTextField(
            value = remember { mutableStateListOf<String>() },
            verticalPadding = 16.dp,
            hasFocus = true,
            shape = RoundedCornerShape(12.dp),
            hint = stringResource(id = R.string.hint_search_chatting),
            onClick = {},
            background = EmotingColors.White,

            ) {
            EmotingIconButton(
                onClick = {},
                painter = painterResource(id = R.drawable.ic_search),
                tint = EmotingColors.Gray300,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {

        }
    }
}

@Composable
private fun SearchChattingContent(
    profileUrl: String,
    name: String,
    time: String,
    emojiUrl: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = profileUrl,
            contentDescription = "profile",
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = name,
                    style = EmotingTypography.TextMedium,
                )
                Text(
                    text = time,
                    style = EmotingTypography.Caption,
                )
            }
            AsyncImage(
                modifier = Modifier.size(24.dp),
                model = emojiUrl,
                contentDescription = "emoji",
            )
        }
    }
}
