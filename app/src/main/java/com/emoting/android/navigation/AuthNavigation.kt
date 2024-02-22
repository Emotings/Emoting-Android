package com.emoting.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.emoting.android.NavigationRoute
import com.emoting.android.feature.landing.LandingScreen
import com.emoting.android.feature.signin.SignInScreen
import com.emoting.android.feature.signup.inputage.InputAgeScreen
import com.emoting.android.feature.signup.inputemail.InputEmailScreen
import com.emoting.android.feature.signup.inputnickname.InputNicknameScreen
import com.emoting.android.feature.signup.inputpassword.InputPasswordScreen
import com.emoting.android.feature.signup.setprofile.SetProfileScreen
import com.emoting.android.feature.splash.SplashScreen

internal fun NavGraphBuilder.authNavigation(
    onBackPressed: () -> Unit,
    navigateToLanding: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToInputEmail: () -> Unit,
    navigateToInputPassword: () -> Unit,
    navigateToSetNickname: () -> Unit,
    navigateToInputAge: () -> Unit,
    navigateToSetProfile: () -> Unit,
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
            SignInScreen()
        }
        composable(NavigationRoute.Auth.INPUT_EMAIL) {
            InputEmailScreen(
                onBackPressed = onBackPressed,
                onNextClick = navigateToInputPassword,
            )
        }
        composable(NavigationRoute.Auth.INPUT_PASSWORD) {
            InputPasswordScreen(
                onBackPressed = onBackPressed,
                onNextClick = navigateToSetNickname,
            )
        }
        composable(NavigationRoute.Auth.INPUT_NICKNAME) {
            InputNicknameScreen(
                onBackPressed = onBackPressed,
                onNextClick = navigateToInputAge,
            )
        }
        composable(NavigationRoute.Auth.INPUT_AGE) {
            InputAgeScreen(
                onBackPressed = onBackPressed,
                onNextClick = navigateToSetProfile,
            )
        }
        composable(NavigationRoute.Auth.SET_PROFILE) {
            SetProfileScreen(
                onBackPressed = onBackPressed,
                onNextClick = {},
            )
        }
    }
}
