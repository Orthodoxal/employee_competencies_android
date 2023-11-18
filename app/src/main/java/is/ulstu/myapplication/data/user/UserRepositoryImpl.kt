package `is`.ulstu.myapplication.data.user

import `is`.ulstu.myapplication.data.user.sources.api.UserSource
import `is`.ulstu.myapplication.data.user.sources.api.entities.AuthUserByTokenRequestEntity
import `is`.ulstu.myapplication.data.user.sources.api.entities.AuthUserRequestEntity
import `is`.ulstu.myapplication.data.user.sources.api.entities.RemoveActiveUserRequestEntity
import `is`.ulstu.myapplication.data.user.sources.cache.UserInfoCache
import `is`.ulstu.myapplication.authorization.domain.AuthorizationRepository
import `is`.ulstu.myapplication.authorization.domain.models.UserInfoModel
import `is`.ulstu.myapplication.base.api.ResponseStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userInfoCache: UserInfoCache,
    private val userSource: UserSource,
    private val userMapper: UserMapper,
) : AuthorizationRepository {
    override suspend fun authorizeUser(login: String, password: String): UserInfoModel =
        userMapper.run { userSource.authorizeUser(AuthUserRequestEntity(login, password)).toUserInfoModel() }

    override suspend fun authorizeUserByToken(token: String): UserInfoModel =
        userMapper.run {
            userSource.authorizeUserByToken(AuthUserByTokenRequestEntity(token)).toUserInfoModel()
        }

    override suspend fun logoutUser(token: String): ResponseStatus =
        userSource.logoutUser(RemoveActiveUserRequestEntity(token)).status

    override suspend fun saveUserInfoToCache(userInfoModel: UserInfoModel) =
        userMapper.run { userInfoCache.setUserInfo(userInfoModel.toUserEntity()) }

    override suspend fun getUserInfoFromCache(): UserInfoModel? =
        userMapper.run { userInfoCache.getUserInfo()?.toUserInfoModel() }

    override suspend fun getUserTokenFromCache(): String? =
        userInfoCache.getUserToken()

    override suspend fun clearUserInfoCache() =
        userInfoCache.removeUserInfo()
}
