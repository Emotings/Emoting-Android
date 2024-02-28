package com.emoting.android.feature.signup.inputpassword

import com.emoting.android.base.BaseViewModel

class InputPasswordViewModel :
    BaseViewModel<InputPasswordState, InputPasswordSideEffect>(InputPasswordState()) {
    internal fun setPassword(password: String) = setState {
        setButtonEnabled()
        state.value.copy(
            password = password,
            passwordErrorState = false,
        )
    }

    internal fun setRepeatPassword(repeatPassword: String) = setState {
        setButtonEnabled()
        state.value.copy(
            repeatPassword = repeatPassword,
            repeatPasswordErrorState = false,
        )
    }

    private fun setButtonEnabled() {
        with(state.value) {
            val hasBlank = password.isBlank() || repeatPassword.isBlank()
            val hasErrorState = passwordErrorState || repeatPasswordErrorState
            setState { copy(buttonEnabled = !hasBlank && !hasErrorState) }
        }
    }

    internal fun onNextClick() {
        val passwordValidate = state.value.password.length in 6..8
        val repeatPasswordValidate = state.value.password == state.value.repeatPassword

        if (passwordValidate && repeatPasswordValidate) {
            postSideEffect(InputPasswordSideEffect.MoveToNext(password = state.value.password))
        }
        setState {
            state.value.copy(
                passwordErrorState = !passwordValidate,
                repeatPasswordErrorState = state.value.password != state.value.repeatPassword,
                buttonEnabled = false,
            )
        }
    }
}

data class InputPasswordState(
    val password: String = "",
    val repeatPassword: String = "",
    val passwordErrorState: Boolean = false,
    val repeatPasswordErrorState: Boolean = false,
    val buttonEnabled: Boolean = false,
)

sealed interface InputPasswordSideEffect {
    data class MoveToNext(val password: String) : InputPasswordSideEffect
}
