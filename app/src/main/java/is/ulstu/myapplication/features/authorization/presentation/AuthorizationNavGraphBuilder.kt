package `is`.ulstu.myapplication.features.authorization.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal const val AUTH_NAV_ROUTE = "authorization"
internal const val AUTH_ROUTE = "login"

fun NavGraphBuilder.authorizationNavGraphBuilder() {
    navigation(route = AUTH_NAV_ROUTE, startDestination = AUTH_ROUTE) {
        composable(route = AUTH_ROUTE) {
            AuthorizationScreen()
        }
    }
}
