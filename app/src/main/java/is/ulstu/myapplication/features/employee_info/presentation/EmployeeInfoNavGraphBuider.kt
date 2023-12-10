package `is`.ulstu.myapplication.features.employee_info.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import `is`.ulstu.myapplication.ui.navigation.routeWithArgs

internal const val EMPLOYEE_INFO_ARGS = "employee_args"
internal const val EMPLOYEE_INFO_ROUTE = "employee_info_screen"

fun NavGraphBuilder.employeeInfoNavGraphBuilder() {
    composable(
        route = routeWithArgs(route = EMPLOYEE_INFO_ROUTE, EMPLOYEE_INFO_ARGS),
        arguments = listOf(navArgument(EMPLOYEE_INFO_ARGS) { type = NavType.LongType })
    ) { backStackEntry ->
        EmployeeInfoScreen(employeeId = backStackEntry.arguments?.getLong(EMPLOYEE_INFO_ARGS))
    }
}
