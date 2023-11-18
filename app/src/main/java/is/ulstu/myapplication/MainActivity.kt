package `is`.ulstu.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import `is`.ulstu.myapplication.authorization.presentation.AUTH_NAV_ROUTE
import `is`.ulstu.myapplication.ui.navigation.NavigationIntent
import `is`.ulstu.myapplication.ui.navigation.Navigator
import `is`.ulstu.myapplication.ui.navigation.mainNavGraphBuilder
import `is`.ulstu.myapplication.ui.theme.EmployeeCompetenciesAndroidTheme
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavigationEffects(
                navigationFlow = navigator.navigationFlow,
                navHostController = navController,
            )

            EmployeeCompetenciesAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = AUTH_NAV_ROUTE) { mainNavGraphBuilder() }
                }
            }
        }
    }
}

@Composable
fun NavigationEffects(navigationFlow: SharedFlow<NavigationIntent>, navHostController: NavHostController) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationFlow) {
        navigationFlow.collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigationBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigationTo   -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.singleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) {
                                inclusive = intent.inclusive
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmployeeCompetenciesAndroidTheme {
        Greeting("Android")
    }
}
