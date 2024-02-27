package com.emoting.android.data.api

object RequestUrl {
    const val BASE_URL = "http://52.79.170.221:8080"
    object Auth {
        private const val path = "/auth"
        const val LOGIN = "$path/login"
        const val SIGN_UP = "$path/signup"
    }
}
