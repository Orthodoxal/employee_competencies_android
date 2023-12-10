package `is`.ulstu.myapplication.features.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `is`.ulstu.myapplication.base.AppException
import `is`.ulstu.myapplication.features.authorization.presentation.AUTH_NAV_ROUTE
import `is`.ulstu.myapplication.features.profile.domain.use_cases.LoadUserInfoUseCase
import `is`.ulstu.myapplication.features.profile.domain.use_cases.SignOutUseCase
import `is`.ulstu.myapplication.ui.navigation.NavigationIntent
import `is`.ulstu.myapplication.ui.navigation.Navigator
import `is`.ulstu.myapplication.ui.navigation.TABS_ROUTE
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val loadUserInfoUseCase: LoadUserInfoUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow(AuthorizationScreenState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        val message = when (throwable) {
            is AppException -> throwable.message
            else            -> "Неизвестная ошибка"
        }
        navigator.navigateBack(NavigationIntent.NavigationBack())
    }

    init {
        viewModelScope.launch(Dispatchers.IO + handler) {
            try {
                val user = loadUserInfoUseCase()
                _state.update { it.copy(userInfoState = UserInfoState.Loaded(user = user)) }
            } catch (e: Exception) {
                signOutUseCase()
                throw e
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            signOutUseCase()
            delay(500)
            navigator.navigateTo(
                NavigationIntent.NavigationTo(
                    route = AUTH_NAV_ROUTE,
                    popUpToRoute = TABS_ROUTE,
                    inclusive = true
                )
            )
        }
    }
}
