package `is`.ulstu.myapplication.authorization.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class AuthorizationScreenState(
    val login: String = "",
    val password: String = "",
    val enterButtonState: EnterButtonState = EnterButtonState.None,
    val error: AuthorizationError = AuthorizationError.None,
)

sealed class EnterButtonState {
    object None : EnterButtonState()
    object Loading : EnterButtonState()
}

sealed class AuthorizationError {
    object None : AuthorizationError()
    data class Error(val message: String): AuthorizationError()
}
