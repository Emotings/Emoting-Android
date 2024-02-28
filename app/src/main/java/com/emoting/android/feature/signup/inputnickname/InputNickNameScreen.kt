package com.emoting.android.feature.signup.inputnickname

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emoting.android.R
import com.emoting.android.feature.signup.SignUpData
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingButton
import com.emoting.designsystem.ui.textfield.EmotingTextField
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.ui.topbar.EmotingTopBar

@Composable
internal fun InputNickNameScreen(
    onBackPressed: () -> Unit,
    navigateToInputAge: (SignUpData) -> Unit,
    signUpData: SignUpData,
    viewModel: InputNickNameViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                is InputNickNameSideEffect.MoveToNext -> {
                    navigateToInputAge(signUpData.copy(nickName = it.nickName))
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        EmotingTopBar(
            onBackPressed = onBackPressed,
            title = stringResource(id = R.string.sign_up),
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = stringResource(id = R.string.enter_nickname),
            style = EmotingTypography.TitleMedium,
        )
        Spacer(modifier = Modifier.height(44.dp))
        EmotingTextField(
            value = state.nickName,
            onValueChange = viewModel::setNickName,
            hint = stringResource(id = R.string.nickname),
        )
        Spacer(modifier = Modifier.weight(1f))
        EmotingButton(
            modifier = Modifier.imePadding(),
            text = stringResource(id = R.string.next),
            color = ButtonColor.Primary,
            onClick = viewModel::onNextClick,
            enabled = state.buttonEnabled
        )
    }
}
