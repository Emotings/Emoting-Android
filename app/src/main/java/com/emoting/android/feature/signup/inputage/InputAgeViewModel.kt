package com.emoting.android.feature.signup.inputage

import com.emoting.android.base.BaseViewModel

internal class InputAgeViewModel :
    BaseViewModel<InputAgeState, InputAgeSideEffect>(InputAgeState()) {
    internal fun setAge(age: String) = setState {
        state.value.copy(
            age = age,
            buttonEnabled = age.isNotBlank(),
        )
    }

    internal fun onNextClick() {
        postSideEffect(InputAgeSideEffect.MoveToNext(age = state.value.age))
    }
}

internal data class InputAgeState(
    val age: String = "",
    val buttonEnabled: Boolean = false,
)

internal sealed interface InputAgeSideEffect {
    data class MoveToNext(val age: String) : InputAgeSideEffect
}
