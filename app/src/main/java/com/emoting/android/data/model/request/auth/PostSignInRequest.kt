package com.emoting.android.data.model.request.auth

import com.google.gson.annotations.SerializedName

internal data class PostSignInRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)
