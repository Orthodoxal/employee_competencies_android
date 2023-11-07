package `is`.ulstu.myapplication.data.sources.api

import `is`.ulstu.myapplication.data.sources.api.entities.AuthUserByTokenRequestEntity
import `is`.ulstu.myapplication.data.sources.api.entities.AuthUserRequestEntity
import `is`.ulstu.myapplication.data.sources.api.entities.AuthUserResponseEntity
import `is`.ulstu.myapplication.data.sources.api.entities.RemoveActiveUserRequestEntity
import `is`.ulstu.myapplication.data.sources.api.entities.RemoveActiveUserResponseEntity

interface UserSource {
    suspend fun authorizeUser(authUserRequestEntity: AuthUserRequestEntity): AuthUserResponseEntity

    suspend fun authorizeUserByToken(authUserByTokenRequestEntity: AuthUserByTokenRequestEntity): AuthUserResponseEntity

    suspend fun logoutUser(removeActiveUserRequestEntity: RemoveActiveUserRequestEntity): RemoveActiveUserResponseEntity
}