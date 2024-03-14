package com.emoting.android.data.api

import com.emoting.android.data.model.request.user.FetchFriendsResponse
import retrofit2.http.GET

interface UserApi {
    @GET(RequestUrl.User.FRIENDS)
    suspend fun fetchFriends(): FetchFriendsResponse
}
