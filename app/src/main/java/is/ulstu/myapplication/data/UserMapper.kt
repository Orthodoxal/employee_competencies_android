package `is`.ulstu.myapplication.data

import `is`.ulstu.myapplication.data.sources.api.entities.AuthUserResponseEntity
import `is`.ulstu.myapplication.data.sources.cache.entities.UserEntity
import `is`.ulstu.myapplication.authorization.domain.models.UserInfoModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserMapper @Inject constructor() {
    fun AuthUserResponseEntity.toUserInfoModel() = UserInfoModel(
        fullName = fullName,
        department = department,
        position = position,
        city = city,
        token = token,
    )

    fun UserEntity.toUserInfoModel() = UserInfoModel(
        fullName = fullName,
        department = department,
        position = position,
        city = city,
        token = token,
    )

    fun UserInfoModel.toUserEntity() = UserEntity(
        fullName = fullName,
        department = department,
        position = position,
        city = city,
        token = token
    )
}
