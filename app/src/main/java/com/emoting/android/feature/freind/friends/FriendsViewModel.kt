package com.emoting.android.feature.freind.friends

import androidx.lifecycle.viewModelScope
import com.emoting.android.base.BaseViewModel
import com.emoting.android.data.api.UserApi
import com.emoting.android.data.model.request.user.FetchFriendsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class FriendsViewModel(
    private val userApi: UserApi,
) : BaseViewModel<FriendsState, FriendsSideEffect>(FriendsState.getInitialState()) {

    init {
        fetchFriends()
    }

    private fun fetchFriends() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                userApi.fetchFriends()
            }.onSuccess {
                setState { state.value.copy(friends = it.users) }
            }
        }
    }
}

internal data class FriendsState(
    val friends: List<FetchFriendsResponse.Friend>,
) {
    companion object {
        fun getInitialState() = FriendsState(
            friends = emptyList(),
        )
    }
}

internal sealed interface FriendsSideEffect {

}
