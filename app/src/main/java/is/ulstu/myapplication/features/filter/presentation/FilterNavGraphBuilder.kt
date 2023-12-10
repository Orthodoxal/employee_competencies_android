package `is`.ulstu.myapplication.features.filter.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal const val FILTER_NAV_ROUTE = "filter"
internal const val FILTER_ROUTE = "filter_screen"

fun NavGraphBuilder.filterNavGraphBuilder() {
    navigation(route = FILTER_NAV_ROUTE, startDestination = FILTER_ROUTE) {
        composable(route = FILTER_ROUTE) {
            EmployeesScreen()
        }
    }
}
