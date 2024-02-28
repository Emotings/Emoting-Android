package com.emoting.android.feature.signup.inputpassword

import androidx.compose.foundation.layout.Arrangement
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
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingButton
import com.emoting.designsystem.ui.textfield.EmotingTextField
import com.emoting.designsystem.ui.theme.EmotingTypography
import com.emoting.designsystem.ui.topbar.EmotingTopBar

@Composable
internal fun InputPasswordScreen(
    onBackPressed: () -> Unit,
    onNextClick: () -> Unit,
    viewModel: InputPasswordViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                is InputPasswordSideEffect.MoveToNext -> {
                    onNextClick()
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
            text = stringResource(id = R.string.enter_password),
            style = EmotingTypography.TitleMedium,
        )
        Spacer(modifier = Modifier.height(44.dp))
        InputPasswordInputs(
            password = state.password,
            repeatPassword = state.repeatPassword,
            onPasswordChange = viewModel::setPassword,
            onRepeatPasswordChange = viewModel::setRepeatPassword,
            passwordErrorState = state.passwordErrorState,
            repeatPasswordErrorState = state.repeatPasswordErrorState,
        )
        Spacer(modifier = Modifier.weight(1f))
        EmotingButton(
            modifier = Modifier.imePadding(),
            text = stringResource(id = R.string.next),
            color = ButtonColor.Primary,
            onClick = viewModel::onNextClick,
            enabled = state.buttonEnabled,
        )
    }
}

@Composable
private fun InputPasswordInputs(
    password: String,
    repeatPassword: String,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    passwordErrorState: Boolean,
    repeatPasswordErrorState: Boolean,
) {
    Column(verticalArrangement = Arrangement.spacedBy(54.dp)) {
        EmotingTextField(
            value = password,
            onValueChange = onPasswordChange,
            hint = stringResource(id = R.string.password),
            description = stringResource(id = R.string.description_password),
            isError = passwordErrorState,
            showVisibleIcon = true,
        )
        EmotingTextField(
            value = repeatPassword,
            onValueChange = onRepeatPasswordChange,
            hint = stringResource(id = R.string.re_enter_password),
            description = stringResource(id = R.string.description_re_enter_password),
            isError = repeatPasswordErrorState,
            showVisibleIcon = true,
        )
    }
}
