package com.emoting.android.feature.freind.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emoting.android.data.api.UserApi

internal class FriendsViewModelFactory(
    private val userApi: UserApi,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FriendsViewModel(userApi = userApi) as T
    }
}
