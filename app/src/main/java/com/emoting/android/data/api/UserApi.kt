package com.emoting.android.data.api

import com.emoting.android.data.model.request.user.FetchFriendsResponse
import com.emoting.android.data.util.RetrofitClient
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @GET(RequestUrl.User.FRIENDS)
    suspend fun fetchFriends(
        @Header("Authorization") accessToken: String = RetrofitClient.ACCESS_TOKEN
    ): FetchFriendsResponse
}
