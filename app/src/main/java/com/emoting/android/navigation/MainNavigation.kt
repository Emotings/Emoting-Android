package com.emoting.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.emoting.android.NavigationArguments
import com.emoting.android.NavigationRoute
import com.emoting.android.feature.chat.ChattingScreen
import com.emoting.android.feature.chat.SearchChattingScreen
import com.emoting.android.feature.freind.FriendRequestsScreen
import com.emoting.android.feature.loading.LoadingScreen
import com.emoting.android.feature.root.RootScreen

internal fun NavGraphBuilder.mainNavigation(
    onBackPressed: () -> Unit,
    navigateToChatting: (Long) -> Unit,
    navigateToSearchChatting: (Long) -> Unit,
) {
    navigation(
        route = NavigationRoute.Main.route,
        startDestination = NavigationRoute.Root.FRIEND_REQUESTS,
    ) {
        composable(NavigationRoute.Main.ROOT) {
            RootScreen(
                navigateToChatting = navigateToChatting,
            )
        }
        composable(NavigationRoute.Main.LOADING) {
            LoadingScreen()
        }
        composable(
            route = "${NavigationRoute.Main.CHATTING}/{${NavigationArguments.CHAT_ID}}",
            arguments = listOf(navArgument(NavigationArguments.CHAT_ID) { NavType.LongType }),
        ) {
            val chatId =
                it.arguments?.getLong(NavigationArguments.CHAT_ID) ?: throw NullPointerException()
            ChattingScreen(
                onBackPressed = onBackPressed,
                chatId = chatId,
                navigateToSearchChatting = navigateToSearchChatting,
            )
        }
        composable(NavigationRoute.Main.CHATTING_SEARCH) {
            SearchChattingScreen()
        }
    }
}
