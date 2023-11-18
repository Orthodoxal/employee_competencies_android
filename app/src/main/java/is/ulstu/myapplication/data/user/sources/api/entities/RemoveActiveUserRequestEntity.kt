package `is`.ulstu.myapplication.data.user.sources.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoveActiveUserRequestEntity(
    val token: String
)
