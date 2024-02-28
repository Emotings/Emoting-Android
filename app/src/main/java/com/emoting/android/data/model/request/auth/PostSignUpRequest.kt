package com.emoting.android.data.model.request.auth

import com.google.gson.annotations.SerializedName

internal data class PostSignUpRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("nickname") val nickName: String,
    @SerializedName("age") val age: Int,
)
