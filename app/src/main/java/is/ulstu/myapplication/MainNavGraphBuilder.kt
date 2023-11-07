package `is`.ulstu.myapplication

import androidx.navigation.NavGraphBuilder
import `is`.ulstu.myapplication.authorization.presentation.authorizationNavGraphBuilder

fun NavGraphBuilder.mainNavGraphBuilder() {
    authorizationNavGraphBuilder()
}
