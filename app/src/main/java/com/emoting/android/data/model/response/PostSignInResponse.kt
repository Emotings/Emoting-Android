package com.emoting.android.data.model.response

import com.google.gson.annotations.SerializedName

internal data class PostSignInResponse(
    @SerializedName("accessToken") val accessToken: String,
)
