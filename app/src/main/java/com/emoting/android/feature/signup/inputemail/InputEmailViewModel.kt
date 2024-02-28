package com.emoting.android.feature.signup.inputemail

import android.util.Patterns
import com.emoting.android.base.BaseViewModel

class InputEmailViewModel :
    BaseViewModel<InputEmailState, InputEmailSideEffect>(InputEmailState()) {
    internal fun setEmail(email: String) = setState {
        state.value.copy(
            email = email,
            buttonEnabled = email.isNotBlank(),
            emailErrorState = false,
        )
    }

    internal fun onNextClick() {
        val emailValidate = Patterns.EMAIL_ADDRESS.matcher(state.value.email).matches()
        if (emailValidate) {
            postSideEffect(InputEmailSideEffect.MoveToNext(email = state.value.email))
        } else {
            setState {
                state.value.copy(
                    buttonEnabled = false,
                    emailErrorState = true,
                )
            }
        }
    }
}

data class InputEmailState(
    val email: String = "",
    val emailErrorState: Boolean = false,
    val buttonEnabled: Boolean = false,
)

sealed interface InputEmailSideEffect {
    data class MoveToNext(val email: String) : InputEmailSideEffect
}
