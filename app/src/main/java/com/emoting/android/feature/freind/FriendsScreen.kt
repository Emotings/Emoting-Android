package com.emoting.android.feature.freind

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.android.ui.ChatContent
import com.emoting.designsystem.ui.button.EmotingIconButton
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.utils.clickable

private data class Friend(
    val profile: String,
    val name: String,
    val notification: Boolean,
)

@Composable
internal fun FriendsScreen(
    navigateToFriendRequests: () -> Unit,
    navigateToSearchFriend: () -> Unit,
) {
    val friends = remember {
        mutableStateListOf(
            Friend(
                profile = "",
                name = "홍길동",
                notification = false,
            ),
            Friend(
                profile = "",
                name = "홍길동",
                notification = true,
            ),
        )
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(EmotingColors.White)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(id = R.string.friends),
                    style = EmotingTypography.TitleSmall,
                )
                EmotingIconButton(
                    onClick = navigateToFriendRequests,
                    painter = painterResource(id = R.drawable.ic_notification),
                    tint = EmotingColors.Gray300,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Friends(friends = friends)
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable(onClick = navigateToSearchFriend)
                .padding(20.dp)
                .shadow(
                    elevation = 1.dp,
                    shape = CircleShape,
                )
                .clip(CircleShape)
                .background(EmotingColors.Primary500)
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_friend),
                contentDescription = "add friend",
                tint = EmotingColors.White,
            )
        }
    }
}

@Composable
private fun Friends(
    friends: SnapshotStateList<Friend>,
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        items(friends) {
            ChatContent(
                profile = it.profile,
                name = it.name,
                boolean = it.notification,
                trueMessage = stringResource(id = R.string.notification_on),
                falseMessage = stringResource(id = R.string.notification_off),
                onClick = {},
            )
        }
    }
}
