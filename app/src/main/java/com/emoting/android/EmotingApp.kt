package com.emoting.android

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.emoting.android.feature.landing.LandingScreen
import com.emoting.android.feature.signin.SignInScreen
import com.emoting.android.feature.signup.inputage.InputAgeScreen
import com.emoting.android.feature.signup.inputemail.InputEmailScreen
import com.emoting.android.feature.signup.inputnickname.InputNicknameScreen
import com.emoting.android.feature.signup.inputpassword.InputPasswordScreen
import com.emoting.android.feature.signup.setprofile.SetProfileScreen
import com.emoting.android.feature.splash.SplashScreen

@Composable
internal fun EmotingApp() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        navController = navController,
        startDestination = NavigationRoute.Auth.route,
    ) {
        authNavigation(
            onBackPressed = navController::popBackStack,
            navigateToLanding = { navController.navigate(NavigationRoute.Auth.LANDING) },
            navigateToSignIn = { navController.navigate(NavigationRoute.Auth.SIGN_IN) },
            navigateToInputEmail = { navController.navigate(NavigationRoute.Auth.INPUT_EMAIL) },
            navigateToInputPassword = { navController.navigate(NavigationRoute.Auth.INPUT_PASSWORD) },
            navigateToSetNickname = { navController.navigate(NavigationRoute.Auth.INPUT_NICKNAME) },
            navigateToInputAge = { navController.navigate(NavigationRoute.Auth.INPUT_AGE) },
            navigateToSetProfile = { navController.navigate(NavigationRoute.Auth.SET_PROFILE) },
        )
    }
}

private fun NavGraphBuilder.authNavigation(
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
