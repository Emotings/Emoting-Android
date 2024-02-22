package com.emoting.android

object NavigationArguments {

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
}
