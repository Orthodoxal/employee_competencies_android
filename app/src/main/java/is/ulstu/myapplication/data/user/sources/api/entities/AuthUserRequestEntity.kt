package `is`.ulstu.myapplication.data.user.sources.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthUserRequestEntity(
    val login: String,
    val password: String,
)
