package `is`.ulstu.myapplication.authorization.domain.use_cases

import `is`.ulstu.myapplication.authorization.domain.AuthorizationRepository
import `is`.ulstu.myapplication.base.api.BusinessLogicException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) {
    suspend fun signIn(login: String, password: String) {
        val userInfoModel = authorizationRepository.authorizeUser(login, password)
        authorizationRepository.saveUserInfoToCache(userInfoModel)
    }

    suspend fun signInByToken() {
        val token =
            authorizationRepository.getUserTokenFromCache() ?: throw BusinessLogicException(message = "Пожалуйста, авторизуйтесь")
        val userInfoModel = authorizationRepository.authorizeUserByToken(token)
        authorizationRepository.saveUserInfoToCache(userInfoModel)
    }
}
