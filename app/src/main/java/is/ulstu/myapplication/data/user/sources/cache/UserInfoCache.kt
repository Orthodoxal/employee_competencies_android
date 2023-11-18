package `is`.ulstu.myapplication.data.user.sources.cache

import `is`.ulstu.myapplication.data.user.sources.cache.entities.UserEntity

interface UserInfoCache {
    fun setUserInfo(userEntity: UserEntity)
    fun getUserInfo(): UserEntity?
    fun getUserToken(): String?
    fun removeUserInfo()
}
