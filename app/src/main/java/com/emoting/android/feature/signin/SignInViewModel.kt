package com.emoting.android.feature.signin

import androidx.lifecycle.viewModelScope
import com.emoting.android.base.BaseViewModel
import com.emoting.android.data.api.AuthApi
import com.emoting.android.data.model.request.auth.PostSignInRequest
import com.emoting.android.data.model.response.PostSignInResponse
import com.emoting.android.data.util.BadRequestException
import com.emoting.android.data.util.NotFoundException
import com.emoting.android.data.util.RequestHandler
import com.emoting.android.data.util.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class SignInViewModel(
    private val authApi: AuthApi,
) : BaseViewModel<SignInState, SignInSideEffect>(SignInState()) {

    internal fun setEmail(email: String) = setState {
        state.value.copy(
            email = email,
            emailErrorState = false,
            buttonEnabled = true,
        )
    }

    internal fun setPassword(password: String) = setState {
        state.value.copy(
            password = password,
            passwordErrorState = false,
            buttonEnabled = true,
        )
    }

    internal fun onNextClick() {
        setState { state.value.copy(buttonEnabled = false) }
        if (checkSignInValidation()) {
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    RequestHandler<PostSignInResponse>().request {
                        authApi.postSignIn(
                            postSignInRequest = PostSignInRequest(
                                email = state.value.email,
                                password = state.value.password,
                            ),
                        )
                    }
                }.onSuccess {
                    saveAccessToken(accessToken = it.accessToken)
                    postSideEffect(SignInSideEffect.Success)
                }.onFailure {
                    setState {
                        state.value.copy(
                            emailErrorState = it is NotFoundException,
                            passwordErrorState = it is BadRequestException,
                        )
                    }
                }
            }
        }
    }

    private fun saveAccessToken(accessToken: String) {
        RetrofitClient.ACCESS_TOKEN = accessToken
    }

    private fun checkSignInValidation(): Boolean {
        with(state.value) {
            if (email.isBlank() || password.isBlank()) {
                postSideEffect(SignInSideEffect.InvalidValue)
                return false
            }
        }

        return true
    }
}

data class SignInState(
    val email: String = "",
    val password: String = "",
    val emailErrorState: Boolean = false,
    val passwordErrorState: Boolean = false,
    val buttonEnabled: Boolean = true,
)

sealed interface SignInSideEffect {
    data object Success : SignInSideEffect
    data object InvalidValue : SignInSideEffect
}
