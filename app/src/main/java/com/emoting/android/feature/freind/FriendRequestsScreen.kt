package com.emoting.android.feature.freind

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.android.ui.ChatContent
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.utils.clickable

@Composable
internal fun FriendRequestsScreen(
    onBackPressed: () -> Unit,
    navigateToSearchFriend: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(EmotingColors.White)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            FriendRequestsTitle(onBackPressed = onBackPressed)
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(28.dp)) {
                items(2) {
                    ChatContent(
                        profile = "",
                        name = "홍길동",
                        boolean = true,
                        trueMessage = "알림 on",
                        falseMessage = "알림 off",
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_refuse),
                            contentDescription = "refuse",
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_approve),
                            contentDescription = "approve",
                        )
                    }
                }
            }
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
private fun FriendRequestsTitle(onBackPressed: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.clickable(onClick = onBackPressed),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                painter = painterResource(id = com.emoting.android.design_system.R.drawable.ic_back),
                contentDescription = "back",
                tint = EmotingColors.Gray300,
            )
            Text(
                text = stringResource(id = R.string.back),
                color = EmotingColors.Gray300,
            )
        }
        Text(
            text = stringResource(id = R.string.friend_requests),
            style = EmotingTypography.TitleSmall,
        )
    }
}
