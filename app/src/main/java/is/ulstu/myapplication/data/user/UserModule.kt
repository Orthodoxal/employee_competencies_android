package `is`.ulstu.myapplication.data.user

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `is`.ulstu.myapplication.data.user.sources.api.UserSource
import `is`.ulstu.myapplication.data.user.sources.api.RetrofitUserSource
import `is`.ulstu.myapplication.data.user.sources.cache.SharedPreferencesInfoUserCache
import `is`.ulstu.myapplication.data.user.sources.cache.UserInfoCache
import `is`.ulstu.myapplication.authorization.domain.AuthorizationRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindUserSource(retrofitUserSource: RetrofitUserSource): UserSource

    @Binds
    abstract fun bindUserInfoCache(sharedPreferencesInfoUserCache: SharedPreferencesInfoUserCache): UserInfoCache

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): AuthorizationRepository
}
