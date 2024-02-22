package com.emoting.android.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.emoting.android.R
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingButton
import com.emoting.designsystem.ui.textfield.EmotingTextField

@Composable
internal fun SignInScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            modifier = Modifier.padding(horizontal = 20.dp),
            painter = painterResource(id = R.drawable.ic_emoting_landing),
            contentDescription = "emoting",
        )
        Spacer(modifier = Modifier.height(50.dp))
        SignInInputs(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
        )
        Spacer(modifier = Modifier.weight(1f))
        EmotingButton(
            modifier = Modifier.imePadding(),
            text = stringResource(id = R.string.sign_in),
            color = ButtonColor.Primary,
            onClick = {},
        )
    }
}

@Composable
private fun SignInInputs(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(54.dp)) {
        EmotingTextField(
            value = email,
            onValueChange = onEmailChange,
            hint = stringResource(id = R.string.email),
        )
        EmotingTextField(
            value = password,
            onValueChange = onPasswordChange,
            hint = stringResource(id = R.string.password),
            showVisibleIcon = true,
        )
    }
}
