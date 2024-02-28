package com.emoting.android.feature.signup.setprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emoting.android.data.api.AuthApi

internal class SetProfileViewModelFactory(
    private val authApi: AuthApi,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SetProfileViewModel(authApi = authApi) as T
    }
}
