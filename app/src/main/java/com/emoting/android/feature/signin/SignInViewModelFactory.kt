package com.emoting.android.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emoting.android.data.api.AuthApi

internal class SignInViewModelFactory(
    private val authApi: AuthApi,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(authApi = authApi) as T
    }
}

