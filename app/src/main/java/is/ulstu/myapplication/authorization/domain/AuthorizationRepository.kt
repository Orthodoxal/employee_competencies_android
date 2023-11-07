package `is`.ulstu.myapplication.authorization.domain

import `is`.ulstu.myapplication.authorization.domain.models.UserInfoModel
import `is`.ulstu.myapplication.base.api.ResponseStatus

interface AuthorizationRepository {
    suspend fun authorizeUser(login: String, password: String): UserInfoModel
    suspend fun authorizeUserByToken(token: String): UserInfoModel
    suspend fun logoutUser(token: String): ResponseStatus
    suspend fun saveUserInfoToCache(userInfoModel: UserInfoModel)
    suspend fun getUserInfoFromCache(): UserInfoModel?
    suspend fun getUserTokenFromCache(): String?
    suspend fun clearUserInfoCache()
}