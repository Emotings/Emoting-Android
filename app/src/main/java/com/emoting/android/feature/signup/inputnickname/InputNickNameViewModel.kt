package com.emoting.android.feature.signup.inputnickname

import com.emoting.android.base.BaseViewModel

class InputNickNameViewModel : BaseViewModel<InputNickNameState, InputNickNameSideEffect>(
    InputNickNameState()
) {
    internal fun setNickName(nickName: String) = setState {
        state.value.copy(
            nickName = nickName,
            buttonEnabled = nickName.isNotBlank()
        )
    }

    internal fun onNextClick() = with(state.value) {
        postSideEffect(InputNickNameSideEffect.MoveToNext(nickName = nickName))
    }
}

data class InputNickNameState(
    val nickName: String = "",
    val buttonEnabled: Boolean = false,
)

sealed interface InputNickNameSideEffect {
    data class MoveToNext(val nickName: String) : InputNickNameSideEffect
}
