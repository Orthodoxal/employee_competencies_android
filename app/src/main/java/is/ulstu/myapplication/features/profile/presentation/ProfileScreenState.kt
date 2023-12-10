package `is`.ulstu.myapplication.features.profile.presentation

import androidx.compose.runtime.Immutable
import `is`.ulstu.myapplication.features.authorization.domain.models.UserInfoModel

@Immutable
data class AuthorizationScreenState(
    val userInfoState: UserInfoState = UserInfoState.Loading,
)

sealed interface UserInfoState {
    data class Loaded(val user: UserInfoModel) : UserInfoState
    object Loading : UserInfoState
}
