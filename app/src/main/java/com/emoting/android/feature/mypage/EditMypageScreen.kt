package com.emoting.android.feature.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingButton
import com.emoting.designsystem.ui.textfield.EmotingTextField
import com.emoting.designsystem.ui.topbar.EmotingTopBar

@Composable
fun EditMyPageScreen(
    onBackPressed: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        EmotingTopBar(
            title = "마이페이지 수정",
            onBackPressed = onBackPressed,
        )
        Text(text = "이름")
        EmotingTextField(
            value = name,
            onValueChange = { name = it },
            hint = "",
        )
        Text(text = "이메일")
        EmotingTextField(
            value = email,
            onValueChange = { email = it },
            hint = "",
        )
        Text(text = "아이디")
        EmotingTextField(
            value = id,
            onValueChange = { id = it },
            hint = "",
        )
        Spacer(modifier = Modifier.weight(1f))
        EmotingButton(
            text = "수정하기",
            color = ButtonColor.Primary,
            onClick = {},
        )
    }
}