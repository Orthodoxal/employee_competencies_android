package `is`.ulstu.myapplication.authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `is`.ulstu.myapplication.authorization.domain.use_cases.SignInUseCase
import `is`.ulstu.myapplication.base.AppException
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
class AuthorizationScreenViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow(AuthorizationScreenState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        val message = when (throwable) {
            is AppException -> throwable.message
            else            -> "Неизвестная ошибка"
        }
        _state.update { it.copy(enterButtonState = EnterButtonState.None, error = AuthorizationError.Error(message = message)) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.update { it.copy(enterButtonState = EnterButtonState.Loading) }
            signInUseCase.signInByToken()
            _state.update { it.copy(enterButtonState = EnterButtonState.None) }
            delay(500L)
            navigator.navigateTo(NavigationIntent.NavigationTo(route = TABS_ROUTE, inclusive = true))
        }
    }

    fun singIn() {
        val (login, password) = state.value
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.update { it.copy(enterButtonState = EnterButtonState.Loading) }
            signInUseCase.signIn(login, password)
            _state.update { it.copy(enterButtonState = EnterButtonState.None) }
            navigator.navigateTo(NavigationIntent.NavigationTo(route = TABS_ROUTE, inclusive = true))
        }
    }

    fun onLoginChanged(login: String) = _state.update { it.copy(login = login) }

    fun onPasswordChanged(password: String) = _state.update { it.copy(password = password) }
}
