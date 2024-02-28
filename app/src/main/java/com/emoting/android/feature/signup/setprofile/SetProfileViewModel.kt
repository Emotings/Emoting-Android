package com.emoting.android.feature.signup.setprofile

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.emoting.android.base.BaseViewModel
import com.emoting.android.data.api.AuthApi
import com.emoting.android.data.model.request.auth.PostSignUpRequest
import com.emoting.android.data.util.ConflictException
import com.emoting.android.data.util.RequestHandler
import com.emoting.android.feature.signup.SignUpData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class SetProfileViewModel(
    private val authApi: AuthApi,
) :
    BaseViewModel<SetProfileState, SetProfileSideEffect>(SetProfileState()) {

    internal fun setUri(uri: Uri?) = setState {
        state.value.copy(
            profileImageUri = uri,
            buttonEnabled = uri != null,
        )
    }

    internal fun onNextClick(signUpData: SignUpData) {
        // TODO 프로필 사진 설정
        setState { state.value.copy(buttonEnabled = false) }
        signUp(signUpData = signUpData)
    }

    internal fun onLaterClick(signUpData: SignUpData) {
        setState { state.value.copy(laterButtonEnabled = false) }
        signUp(signUpData = signUpData)
    }

    private fun signUp(signUpData: SignUpData) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                RequestHandler<Unit>().request {
                    with(signUpData) {
                        authApi.postSignUp(
                            postSignUpRequest = PostSignUpRequest(
                                email = email,
                                password = password,
                                nickName = nickName,
                                age = age,
                            )
                        )
                    }
                }
            }.onSuccess {
                postSideEffect(SetProfileSideEffect.SuccessSignUp)
            }.onFailure {
                // TODO 예외 처리
                when (it) {
                    is ConflictException -> {
                        postSideEffect(SetProfileSideEffect.AlreadyExists)
                    }
                }
            }
        }
    }
}

data class SetProfileState(
    val profileImageUri: Uri? = null,
    val laterButtonEnabled: Boolean = false,
    val buttonEnabled: Boolean = false,
)

sealed interface SetProfileSideEffect {
    data object SuccessSignUp : SetProfileSideEffect
    data object AlreadyExists: SetProfileSideEffect
}
