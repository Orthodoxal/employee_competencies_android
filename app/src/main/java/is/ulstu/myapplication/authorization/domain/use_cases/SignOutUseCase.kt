package `is`.ulstu.myapplication.authorization.domain.use_cases

import `is`.ulstu.myapplication.authorization.domain.AuthorizationRepository
import `is`.ulstu.myapplication.base.BusinessLogicException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignOutUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) {
    suspend operator fun invoke() {
        val token =
            authorizationRepository.getUserTokenFromCache() ?: throw BusinessLogicException(message = "Пожалуйста, авторизуйтесь")
        authorizationRepository.logoutUser(token)
        authorizationRepository.clearUserInfoCache()
    }
}
