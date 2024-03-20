package com.emoting.android.feature.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emoting.android.R
import com.emoting.designsystem.ui.theme.EmotingColors
import com.emoting.designsystem.ui.theme.EmotingTypography.TextLarge
import com.emoting.designsystem.ui.theme.EmotingTypography.TextMedium
import com.emoting.designsystem.utils.clickable

@Composable
fun MyPageScreen(
    navigateToEdit: () -> Unit,
) {
    var check by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "abcd1234",
                style = TextLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "알림",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = check, onCheckedChange = { check = it })
        }
        Row(
            modifier = Modifier.clickable { navigateToEdit() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_my_page),
                contentDescription = "profile",
                Modifier.size(60.dp),
            )
            Column {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "aaa",
                        style = TextLarge,
                    )
                    Text(
                        text = "aaa",
                        color = EmotingColors.Primary500,
                        style = TextMedium,
                    )
                    Text(
                        text = "aaa",
                        color = EmotingColors.Gray100,
                        style = TextMedium,
                    )
                }
                Text(
                    text = "asdasdasdasdads",
                    color = EmotingColors.Gray100,
                    style = TextMedium,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "settings",
                tint = EmotingColors.Gray100,
            )
        }
    }
}