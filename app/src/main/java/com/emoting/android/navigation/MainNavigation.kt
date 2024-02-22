package com.emoting.android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.emoting.android.NavigationRoute
import com.emoting.android.feature.root.RootScreen

internal fun NavGraphBuilder.mainNavigation(
    onBackPressed: () -> Unit,
) {
    composable(NavigationRoute.Main.ROOT) {
        RootScreen()
    }
}
