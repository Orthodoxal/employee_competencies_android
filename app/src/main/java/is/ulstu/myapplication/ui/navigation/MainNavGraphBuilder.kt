package `is`.ulstu.myapplication.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import `is`.ulstu.myapplication.R
import `is`.ulstu.myapplication.authorization.presentation.authorizationNavGraphBuilder
import `is`.ulstu.myapplication.employees.presentation.EMPLOYEES_NAV_ROUTE
import `is`.ulstu.myapplication.employees.presentation.employeesNavGraphBuilder
import `is`.ulstu.myapplication.ui.theme.White

internal const val TABS_ROUTE = "tabs"

fun NavGraphBuilder.mainNavGraphBuilder() {
    authorizationNavGraphBuilder()
    composable(route = TABS_ROUTE) { TabsScreen() }
}

private fun NavGraphBuilder.tabsNavGraphBuilder() {
    employeesNavGraphBuilder()
}

private val tabsItems = listOf(
    TabsItem(iconRes = R.drawable.ic_employees_catalog, route = EMPLOYEES_NAV_ROUTE, name = "Сотрудники"),
    TabsItem(iconRes = R.drawable.ic_filter, route = "", name = "Фильтр"),
    TabsItem(iconRes = R.drawable.ic_profile, route = "", name = "Профиль"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TabsScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                tabsItems.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = ImageVector.vectorResource(id = item.iconRes),
                                tint = White,
                                contentDescription = null,
                            )
                        },
                        label = { Text(text = item.name) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = EMPLOYEES_NAV_ROUTE, Modifier.padding(innerPadding)) {
            tabsNavGraphBuilder()
        }
    }
}

private data class TabsItem(
    @DrawableRes
    val iconRes: Int,
    val route: String,
    val name: String,
)
