package com.emoting.android.feature.signup.setprofile

import android.net.Uri
import com.emoting.android.base.BaseViewModel

class SetProfileViewModel :
    BaseViewModel<SetProfileState, SetProfileSideEffect>(SetProfileState()) {

    internal fun setUri(uri: Uri?) = setState {
        state.value.copy(
            profileImageUri = uri,
            buttonEnabled = uri != null,
        )
    }

    internal fun onNextClick() {

    }

    internal fun onLaterClick() {

    }
}

data class SetProfileState(
    val profileImageUri: Uri? = null,
    val buttonEnabled: Boolean = false,
)

sealed interface SetProfileSideEffect {
    data object SuccessSignUp : SetProfileSideEffect
}
