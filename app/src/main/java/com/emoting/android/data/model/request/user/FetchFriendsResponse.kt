package com.emoting.android.data.model.request.user

import com.google.gson.annotations.SerializedName

data class FetchFriendsResponse(
    @SerializedName("users") val users: List<Friend>
) {
    data class Friend(
        @SerializedName("nickname") val nickName: String,
        @SerializedName("profile") val profile: String,
        @SerializedName("isTurnOn") val isTurnOn: Boolean,
    )
}
