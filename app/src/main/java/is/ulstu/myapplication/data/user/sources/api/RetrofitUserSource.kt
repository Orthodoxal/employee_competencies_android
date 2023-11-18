package `is`.ulstu.myapplication.data.user.sources.api

import `is`.ulstu.myapplication.data.user.sources.api.entities.AuthUserByTokenRequestEntity
import `is`.ulstu.myapplication.data.user.sources.api.entities.AuthUserRequestEntity
import `is`.ulstu.myapplication.data.user.sources.api.entities.AuthUserResponseEntity
import `is`.ulstu.myapplication.data.user.sources.api.entities.RemoveActiveUserRequestEntity
import `is`.ulstu.myapplication.data.user.sources.api.entities.RemoveActiveUserResponseEntity
import `is`.ulstu.myapplication.base.api.BaseRetrofitSource
import `is`.ulstu.myapplication.base.api.RetrofitConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitUserSource @Inject constructor(
    config: RetrofitConfig
) : BaseRetrofitSource(config), UserSource {
    private val basicIndicatorsApi = retrofit.create(UserApi::class.java)

    override suspend fun authorizeUser(authUserRequestEntity: AuthUserRequestEntity): AuthUserResponseEntity =
        wrapRetrofitExceptions { basicIndicatorsApi.authorizeUser(authUserRequestEntity) }

    override suspend fun authorizeUserByToken(
        authUserByTokenRequestEntity: AuthUserByTokenRequestEntity
    ): AuthUserResponseEntity =
        wrapRetrofitExceptions { basicIndicatorsApi.authorizeUserByToken(authUserByTokenRequestEntity) }

    override suspend fun logoutUser(
        removeActiveUserRequestEntity: RemoveActiveUserRequestEntity
    ): RemoveActiveUserResponseEntity =
        wrapRetrofitExceptions { basicIndicatorsApi.logoutUser(removeActiveUserRequestEntity) }
}
