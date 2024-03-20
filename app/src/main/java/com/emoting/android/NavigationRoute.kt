package com.emoting.android

object NavigationArguments {
    const val CHAT_ID = "chatId"
    const val SIGN_UP = "signUp"
}

sealed class NavigationRoute(val route: String) {
    data object Auth : NavigationRoute(route = "/auth") {
        val SPLASH = this.route + "/splash"
        val LANDING = this.route + "/landing"
        val SIGN_IN = this.route + "/signIn"
        val INPUT_EMAIL = this.route + "/inputEmail"
        val INPUT_PASSWORD = this.route + "/inputPassword"
        val INPUT_NICKNAME = this.route + "/inputNickname"
        val INPUT_AGE = this.route + "/inputAge"
        val SET_PROFILE = this.route + "/setProfile"
    }

    data object Main : NavigationRoute(route = "/main") {
        val LOADING = this.route + "/loading"
        val EMOJI_REGISTRATION = this.route + "emojiRegistration"
        val ROOT = this.route + "/root"
        val CHATTING = this.route + "/chatting"
        val CHATTING_SEARCH = "$CHATTING/search"
        val EDIT_MY_PAGE = this.route + "/edit"
    }

    data object Root : NavigationRoute(route = "/root") {
        val CHAT = this.route + "/chat"
        val FRIENDS = this.route + "/friends"
        val STORE = this.route + "/store"
        val MY_PAGE = this.route + "/myPage"
        val FRIEND_REQUESTS = this.route + "/friendRequests"
        val SEARCH_FRIEND = this.route + "/searchFriend"
    }
}
