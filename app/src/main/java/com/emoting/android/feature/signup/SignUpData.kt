package com.emoting.android.feature.signup

import kotlinx.serialization.Serializable

@Serializable
data class SignUpData(
    val email: String = "",
    val password: String = "",
    val nickName: String = "",
    val age: Int = 0,
)
