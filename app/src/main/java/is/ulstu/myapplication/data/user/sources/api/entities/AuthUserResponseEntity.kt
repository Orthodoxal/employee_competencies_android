package `is`.ulstu.myapplication.data.user.sources.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthUserResponseEntity(
    val fullName: String,
    val department: String,
    val position: String,
    val city: String,
    val token: String,
)
