package com.emoting.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.emoting.android.NavigationRoute
import com.emoting.android.feature.chat.ChattingScreen
import com.emoting.android.feature.loading.LoadingScreen
import com.emoting.android.feature.root.RootScreen

internal fun NavGraphBuilder.mainNavigation(
    onBackPressed: () -> Unit,
) {
    navigation(
        route = NavigationRoute.Main.route,
        startDestination = NavigationRoute.Main.ROOT,
    ) {
        composable(NavigationRoute.Main.ROOT) {
            RootScreen()
        }
        composable(NavigationRoute.Main.LOADING) {
            LoadingScreen()
        }
        composable(NavigationRoute.Main.CHATTING) {
            ChattingScreen(onBackPressed = onBackPressed)
        }
    }
}
