package `is`.ulstu.myapplication.employees.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal const val EMPLOYEES_NAV_ROUTE = "employees"
internal const val EMPLOYEES_ROUTE = "employees_catalog"

fun NavGraphBuilder.employeesNavGraphBuilder() {
    navigation(route = EMPLOYEES_NAV_ROUTE, startDestination = EMPLOYEES_ROUTE) {
        composable(route = EMPLOYEES_ROUTE) {
            EmployeesScreen()
        }
    }
}
