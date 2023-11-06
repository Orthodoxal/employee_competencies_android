package `is`.ulstu.myapplication

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.mainNavGraphBuilder() {
    navigation(startDestination = "login", route = "authorization") {
        composable(route = "login") {
            Greeting("Android")
        }
    }
}
