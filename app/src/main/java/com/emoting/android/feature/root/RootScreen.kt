package com.emoting.android.feature.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emoting.android.NavigationRoute
import com.emoting.android.feature.chat.ChatsScreen
import com.emoting.android.feature.freind.FriendRequestsScreen
import com.emoting.android.feature.freind.FriendsScreen
import com.emoting.android.feature.freind.SearchFriendScreen
import com.emoting.android.navigation.BottomMenu
import com.emoting.android.ui.BottomNavigationBar

@Composable
fun RootScreen(
    navigateToChatting: (Long) -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
        NavHost(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
            navController = navController,
            startDestination = BottomMenu.Chat.route,
        ) {
            composable(BottomMenu.Chat.route) {
                ChatsScreen(navigateToChatting = navigateToChatting)
            }
            composable(BottomMenu.Friends.route) {
                FriendsScreen(
                    navigateToFriendRequests = { navController.navigate(NavigationRoute.Root.FRIEND_REQUESTS) },
                    navigateToSearchFriend = { navController.navigate(NavigationRoute.Root.SEARCH_FRIEND) },
                )
            }
            composable(BottomMenu.Store.route) {

            }
            composable(BottomMenu.MyPage.route) {

            }
            composable(NavigationRoute.Root.FRIEND_REQUESTS) {
                FriendRequestsScreen(
                    onBackPressed = navController::popBackStack,
                    navigateToSearchFriend = { navController.navigate(NavigationRoute.Root.SEARCH_FRIEND) })
            }
            composable(NavigationRoute.Root.SEARCH_FRIEND) {
                SearchFriendScreen()
            }
        }
    }
}
