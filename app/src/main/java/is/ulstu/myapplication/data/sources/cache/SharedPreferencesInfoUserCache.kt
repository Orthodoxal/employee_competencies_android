package `is`.ulstu.myapplication.data.sources.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import `is`.ulstu.myapplication.data.sources.cache.entities.UserEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesInfoUserCache @Inject constructor(
    @ApplicationContext appContext: Context
) : UserInfoCache {
    private val sharedPreferences: SharedPreferences

    init {
        val masterKey: MasterKey = MasterKey.Builder(appContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            appContext,
            "user_info",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun setUserInfo(userEntity: UserEntity): Unit = sharedPreferences.edit()
        .putString(USER_INFO, Json.encodeToString(userEntity))
        .apply()

    override fun getUserInfo(): UserEntity? = sharedPreferences.getString(USER_INFO, null)
        ?.let { Json.decodeFromString(it) }

    override fun getUserToken(): String? = getUserInfo()?.token

    override fun removeUserInfo() = sharedPreferences.edit().remove(USER_INFO).apply()


    companion object {
        private const val USER_INFO = "USER_INFO"
    }
}