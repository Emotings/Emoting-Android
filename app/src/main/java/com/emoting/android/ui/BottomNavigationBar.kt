package com.emoting.android.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emoting.android.navigation.BottomMenu
import com.emoting.designsystem.ui.theme.EmotingColors

val menus = listOf(
    BottomMenu.Chat,
    BottomMenu.Friends,
    BottomMenu.Store,
    BottomMenu.MyPage,
)

@Composable
internal fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Column {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(EmotingColors.Gray50),
        )
        BottomAppBar(containerColor = EmotingColors.White) {
            menus.forEach {
                val selected = currentRoute == it.route
                val tint by animateColorAsState(
                    targetValue = if (selected) EmotingColors.Primary500
                    else EmotingColors.Gray300,
                    label = "",
                )

                NavigationBarItem(
                    selected = currentRoute == it.route,
                    onClick = {
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = it.route,
                            tint = tint,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = EmotingColors.Gray100),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBottomBar() {
    BottomNavigationBar(navController = rememberNavController())
}
