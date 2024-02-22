package com.emoting.android.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
