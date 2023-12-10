package `is`.ulstu.myapplication.features.profile.domain.use_cases

import `is`.ulstu.myapplication.base.BusinessLogicException
import `is`.ulstu.myapplication.features.authorization.domain.AuthorizationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadUserInfoUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) {
    suspend operator fun invoke() =
        authorizationRepository.getUserInfoFromCache() ?: throw BusinessLogicException(message = "Пожалуйста, авторизуйтесь")
}