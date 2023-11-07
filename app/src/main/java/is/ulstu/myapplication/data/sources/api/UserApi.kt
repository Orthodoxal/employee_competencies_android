package `is`.ulstu.myapplication.data.sources.api

import `is`.ulstu.myapplication.data.sources.api.entities.AuthUserByTokenRequestEntity
import `is`.ulstu.myapplication.data.sources.api.entities.AuthUserRequestEntity
import `is`.ulstu.myapplication.data.sources.api.entities.AuthUserResponseEntity
import `is`.ulstu.myapplication.data.sources.api.entities.RemoveActiveUserRequestEntity
import `is`.ulstu.myapplication.data.sources.api.entities.RemoveActiveUserResponseEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("auth")
    suspend fun authorizeUser(@Body authUserRequestEntity: AuthUserRequestEntity): AuthUserResponseEntity

    @POST("authByToken")
    suspend fun authorizeUserByToken(@Body authUserByTokenRequestEntity: AuthUserByTokenRequestEntity): AuthUserResponseEntity

    @POST("logout")
    suspend fun logoutUser(@Body removeActiveUserRequestEntity: RemoveActiveUserRequestEntity): RemoveActiveUserResponseEntity
}