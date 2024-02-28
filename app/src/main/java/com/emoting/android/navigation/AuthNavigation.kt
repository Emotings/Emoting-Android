package com.emoting.android.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.emoting.android.NavigationArguments
import com.emoting.android.NavigationRoute
import com.emoting.android.feature.landing.LandingScreen
import com.emoting.android.feature.signin.SignInScreen
import com.emoting.android.feature.signup.SignUpData
import com.emoting.android.feature.signup.inputage.InputAgeScreen
import com.emoting.android.feature.signup.inputemail.InputEmailScreen
import com.emoting.android.feature.signup.inputnickname.InputNickNameScreen
import com.emoting.android.feature.signup.inputpassword.InputPasswordScreen
import com.emoting.android.feature.signup.setprofile.SetProfileScreen
import com.emoting.android.feature.splash.SplashScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun NavGraphBuilder.authNavigation(
    onBackPressed: () -> Unit,
    navigateToLanding: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToInputEmail: () -> Unit,
    navigateToInputPassword: (SignUpData) -> Unit,
    navigateToInputNickName: (SignUpData) -> Unit,
    navigateToInputAge: (SignUpData) -> Unit,
    navigateToSetProfile: (SignUpData) -> Unit,
    navigateToRoot: () -> Unit,
) {
    navigation(
        startDestination = NavigationRoute.Auth.SPLASH,
        route = NavigationRoute.Auth.route,
    ) {
        composable(NavigationRoute.Auth.SPLASH) {
            SplashScreen(navigateToLanding = navigateToLanding)
        }
        composable(NavigationRoute.Auth.LANDING) {
            LandingScreen(
                onSignInClick = navigateToSignIn,
                onSignUpClick = navigateToInputEmail,
            )
        }
        composable(NavigationRoute.Auth.SIGN_IN) {
            SignInScreen(navigateToMain = navigateToRoot)
        }
        composable(NavigationRoute.Auth.INPUT_EMAIL) {
            InputEmailScreen(
                onBackPressed = onBackPressed,
                navigateToInputPassword = navigateToInputPassword,
            )
        }
        composable(
            route = "${NavigationRoute.Auth.INPUT_PASSWORD}/{${NavigationArguments.SIGN_UP}}",
            arguments = listOf(navArgument(NavigationArguments.SIGN_UP) { NavType.StringType }),
        ) {
            InputPasswordScreen(
                onBackPressed = onBackPressed,
                navigateToSetNickName = navigateToInputNickName,
                signUpData = it.getSignUpData(),
            )
        }
        composable(
            route = "${NavigationRoute.Auth.INPUT_NICKNAME}/{${NavigationArguments.SIGN_UP}}",
            arguments = listOf(navArgument(NavigationArguments.SIGN_UP) { NavType.StringType }),
        ) {
            InputNickNameScreen(
                onBackPressed = onBackPressed,
                navigateToInputAge = navigateToInputAge,
                signUpData = it.getSignUpData(),
            )
        }
        composable(
            route = "${NavigationRoute.Auth.INPUT_AGE}/{${NavigationArguments.SIGN_UP}}",
            arguments = listOf(navArgument(NavigationArguments.SIGN_UP) { NavType.StringType }),
        ) {
            InputAgeScreen(
                onBackPressed = onBackPressed,
                navigateToSetProfile = navigateToSetProfile,
                signUpData = it.getSignUpData(),
            )
        }
        composable(
            route = "${NavigationRoute.Auth.SET_PROFILE}/{${NavigationArguments.SIGN_UP}}",
            arguments = listOf(navArgument(NavigationArguments.SIGN_UP) { NavType.StringType }),
        ) {
            SetProfileScreen(
                onBackPressed = onBackPressed,
                navigateToLanding = navigateToLanding,
                signUpData = it.getSignUpData(),
            )
        }
    }
}

internal fun SignUpData.toJsonString(): String {
    val jsonString = Json.encodeToString(this)
    return jsonString
}

internal fun NavBackStackEntry.getSignUpData(): SignUpData {
    val signUpData =
        arguments?.getString(NavigationArguments.SIGN_UP) ?: throw NullPointerException()
    return Json.decodeFromString(signUpData)
}
