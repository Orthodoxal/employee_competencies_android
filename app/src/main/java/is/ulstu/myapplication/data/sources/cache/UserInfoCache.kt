package `is`.ulstu.myapplication.data.sources.cache

import `is`.ulstu.myapplication.data.sources.cache.entities.UserEntity

interface UserInfoCache {
    fun setUserInfo(userEntity: UserEntity)
    fun getUserInfo(): UserEntity?
    fun getUserToken(): String?
    fun removeUserInfo()
}