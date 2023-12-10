package `is`.ulstu.myapplication.data.user

import `is`.ulstu.myapplication.data.user.sources.api.entities.AuthUserResponseEntity
import `is`.ulstu.myapplication.data.user.sources.cache.entities.UserEntity
import `is`.ulstu.myapplication.features.authorization.domain.models.UserInfoModel
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
