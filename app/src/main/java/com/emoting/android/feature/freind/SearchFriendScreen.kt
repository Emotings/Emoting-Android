package com.emoting.android.feature.freind

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.designsystem.ui.button.EmotingIconButton
import com.emoting.designsystem.ui.textfield.EmotingBoxTextField
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography

@Composable
internal fun SearchFriendScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EmotingColors.White)
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        EmotingBoxTextField(
            value = "",
            hint = stringResource(id = R.string.search_friend_hint),
            onValueChange = {},
        ) {
            EmotingIconButton(
                onClick = {},
                painter = painterResource(id = R.drawable.ic_search),
            )
        }
    }
}

@Composable
private fun NotFoundFriendContent() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Image(
            modifier = Modifier.size(
                width = 120.dp,
                height = 110.dp,
            ),
            painter = painterResource(id = R.drawable.ic_my_page),
            contentDescription = "person",
        )
        Text(
            text = stringResource(id = R.string.not_found_id),
            color = EmotingColors.Gray300,
            style = EmotingTypography.TextMedium,
        )
    }
}
