package `is`.ulstu.myapplication.data.repository

import `is`.ulstu.myapplication.base.api.ResponseStatus
import `is`.ulstu.myapplication.features.authorization.domain.AuthorizationRepository
import `is`.ulstu.myapplication.features.authorization.domain.models.UserInfoModel
import java.util.UUID

class UserRepositoryTest : AuthorizationRepository {
    private var tokenApi: String = UUID.randomUUID().toString()
    private var cacheUser: UserInfoModel? = null

    override suspend fun authorizeUser(login: String, password: String): UserInfoModel =
        if (login == USER_LOGIN && password == USER_PASSWORD)
            user.copy(token = tokenApi)
        else
            error("Not authorized")

    override suspend fun authorizeUserByToken(token: String): UserInfoModel =
        if (tokenApi == token)
            user.copy(token = tokenApi).also { cacheUser = user }
        else
            error("Not authorized")

    override suspend fun logoutUser(token: String): ResponseStatus =
        ResponseStatus.SUCCESS.also { tokenApi = UUID.randomUUID().toString() }

    override suspend fun saveUserInfoToCache(userInfoModel: UserInfoModel) {
        cacheUser = userInfoModel
    }

    override suspend fun getUserInfoFromCache(): UserInfoModel? = cacheUser

    override suspend fun getUserTokenFromCache(): String? = cacheUser?.token

    override suspend fun clearUserInfoCache() {
        cacheUser = null
    }

    companion object {
        const val USER_LOGIN = "login"
        const val USER_PASSWORD = "password"

        private val user = UserInfoModel(
            fullName = "test",
            department = "test",
            position = "test",
            city = "test",
            token = "token",
        )
    }
}
