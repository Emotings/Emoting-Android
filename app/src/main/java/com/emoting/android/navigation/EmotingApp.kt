package com.emoting.android.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.emoting.android.NavigationRoute
import com.emoting.designsystem.ui.theme.EmotingColors

@Composable
internal fun EmotingApp() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier
            .background(EmotingColors.White)
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
            navigateToInputPassword = { navController.navigate("${NavigationRoute.Auth.INPUT_PASSWORD}/${it.toJsonString()}") },
            navigateToInputNickName = { navController.navigate("${NavigationRoute.Auth.INPUT_NICKNAME}/${it.toJsonString()}") },
            navigateToInputAge = { navController.navigate("${NavigationRoute.Auth.INPUT_AGE}/${it.toJsonString()}") },
            navigateToSetProfile = { navController.navigate("${NavigationRoute.Auth.SET_PROFILE}/${it.toJsonString()}") },
            navigateToRoot = {
                navController.navigate(NavigationRoute.Main.ROOT) {
                    popUpTo(NavigationRoute.Auth.SIGN_IN)
                }
            },
        )
        mainNavigation(
            onBackPressed = navController::popBackStack,
            navigateToChatting = { navController.navigate("${NavigationRoute.Main.CHATTING}/$it") },
            navigateToSearchChatting = { navController.navigate(NavigationRoute.Main.CHATTING_SEARCH) },
            navigateToEdit = { navController.navigate(NavigationRoute.Main.EDIT_MY_PAGE) },
        )
    }
}
