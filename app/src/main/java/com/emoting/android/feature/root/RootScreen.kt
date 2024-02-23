package com.emoting.android.feature.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emoting.android.feature.chat.ChatsScreen
import com.emoting.android.navigation.BottomMenu
import com.emoting.android.ui.BottomNavigationBar

@Composable
fun RootScreen() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
        NavHost(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
            navController = navController,
            startDestination = BottomMenu.Chat.route,
        ) {
            composable(BottomMenu.Chat.route) {
                ChatsScreen()
            }
            composable(BottomMenu.Friends.route) {

            }
            composable(BottomMenu.Store.route) {

            }
            composable(BottomMenu.MyPage.route) {

            }
        }
    }
}
