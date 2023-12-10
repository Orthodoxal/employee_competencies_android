package `is`.ulstu.myapplication.features.profile.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal const val PROFILE_NAV_ROUTE = "profile"
internal const val PROFILE_ROUTE = "profile_screen"

fun NavGraphBuilder.profileNavGraphBuilder() {
    navigation(route = PROFILE_NAV_ROUTE, startDestination = PROFILE_ROUTE) {
        composable(route = PROFILE_ROUTE) {
            ProfileScreen()
        }
    }
}
