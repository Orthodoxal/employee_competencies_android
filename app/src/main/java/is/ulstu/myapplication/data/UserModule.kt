package `is`.ulstu.myapplication.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `is`.ulstu.myapplication.data.sources.api.UserSource
import `is`.ulstu.myapplication.data.sources.api.RetrofitUserSource
import `is`.ulstu.myapplication.data.sources.cache.SharedPreferencesInfoUserCache
import `is`.ulstu.myapplication.data.sources.cache.UserInfoCache
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