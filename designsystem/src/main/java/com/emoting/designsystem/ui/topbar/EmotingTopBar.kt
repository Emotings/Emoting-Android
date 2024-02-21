package com.emoting.designsystem.ui.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.emoting.android.design_system.R
import com.emoting.designsystem.ui.button.EmotingIconButton
import com.emoting.designsystem.ui.theme.EmotingTypography

@Composable
fun EmotingTopBar(
    onBackPressed: (() -> Unit)? = null,
    title: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            style = EmotingTypography.TextMedium,
        )
        onBackPressed?.run {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                EmotingIconButton(
                    onClick = onBackPressed,
                    painter = painterResource(id = R.drawable.ic_back),
                )
            }
        }
    }
}
