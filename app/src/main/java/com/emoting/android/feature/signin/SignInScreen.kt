package com.emoting.android.feature.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emoting.android.R
import com.emoting.android.data.util.RetrofitClient
import com.emoting.designsystem.ui.button.ButtonColor
import com.emoting.designsystem.ui.button.EmotingButton
import com.emoting.designsystem.ui.textfield.EmotingTextField

@Composable
internal fun SignInScreen(
    navigateToMain: () -> Unit,
    viewModel: SignInViewModel = viewModel(
        factory = SignInViewModelFactory(
            authApi = RetrofitClient.getAuthApi(),
        ),
    ),
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                SignInSideEffect.Success -> {
                    navigateToMain()
                }

                SignInSideEffect.InvalidValue -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.sign_in_invalid_value),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            modifier = Modifier.padding(horizontal = 20.dp),
            painter = painterResource(id = R.drawable.ic_emoting_landing),
            contentDescription = "emoting",
        )
        Spacer(modifier = Modifier.height(50.dp))
        SignInInputs(
            email = state.email,
            password = state.password,
            onEmailChange = viewModel::setEmail,
            onPasswordChange = viewModel::setPassword,
            emailErrorState = state.emailErrorState,
            passwordErrorState = state.passwordErrorState,
        )
        Spacer(modifier = Modifier.weight(1f))
        EmotingButton(
            modifier = Modifier.imePadding(),
            text = stringResource(id = R.string.sign_in),
            color = ButtonColor.Primary,
            onClick = viewModel::onNextClick,
            enabled = state.buttonEnabled,
        )
    }
}

@Composable
private fun SignInInputs(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    emailErrorState: Boolean,
    passwordErrorState: Boolean,
) {
    Column(verticalArrangement = Arrangement.spacedBy(54.dp)) {
        EmotingTextField(
            value = email,
            onValueChange = onEmailChange,
            hint = stringResource(id = R.string.email),
            isError = emailErrorState,
            description = stringResource(id = R.string.sign_in_not_found_user),
        )
        EmotingTextField(
            value = password,
            onValueChange = onPasswordChange,
            hint = stringResource(id = R.string.password),
            showVisibleIcon = true,
            isError = passwordErrorState,
            description = stringResource(id = R.string.sign_in_invalid_password),
        )
    }
}
