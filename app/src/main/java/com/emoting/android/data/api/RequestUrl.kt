package com.emoting.android.data.api

object RequestUrl {
    object Auth {
        private const val path = "/auth"
        const val LOGIN = "$path/login"
        const val SIGN_UP = "$path/signup"
    }
}
