package com.emoting.android.navigation

import androidx.annotation.DrawableRes
import com.emoting.android.NavigationRoute
import com.emoting.android.R

sealed class BottomMenu(
    @DrawableRes val icon: Int,
    val route: String,
) {
    data object Chat : BottomMenu(
        icon = R.drawable.ic_chat,
        route = NavigationRoute.Root.CHAT,
    )

    data object Friends : BottomMenu(
        icon = R.drawable.ic_add_friend,
        route = NavigationRoute.Root.FRIENDS,
    )

    data object Store : BottomMenu(
        icon = R.drawable.ic_store,
        route = NavigationRoute.Root.STORE,
    )

    data object MyPage : BottomMenu(
        icon = R.drawable.ic_my_page,
        route = NavigationRoute.Root.MY_PAGE,
    )
}
