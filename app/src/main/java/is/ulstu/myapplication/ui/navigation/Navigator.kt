package `is`.ulstu.myapplication.ui.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
    private val _navigationFlow = MutableSharedFlow<NavigationIntent>(extraBufferCapacity = 1)
    val navigationFlow = _navigationFlow.asSharedFlow()

    fun navigateTo(navigationIntent: NavigationIntent.NavigationTo) = _navigationFlow.tryEmit(navigationIntent)

    fun navigateBack(navigationIntent: NavigationIntent.NavigationBack) = _navigationFlow.tryEmit(navigationIntent)
}

data class NavDestination(val route: String, val builder: NavOptionsBuilder.() -> Unit)

sealed class NavigationIntent {
    data class NavigationBack(
        val route: String? = null,
        val inclusive: Boolean = false,
    ) : NavigationIntent()

    data class NavigationTo(
        val route: String,
        val popUpToRoute: String? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = false,
    ) : NavigationIntent()
}

fun routeWithArgs(route: String, vararg params: String) = if (params.isEmpty()) route else buildString {
    append(route)
    params.forEach { append("/{${it}}") }
}
