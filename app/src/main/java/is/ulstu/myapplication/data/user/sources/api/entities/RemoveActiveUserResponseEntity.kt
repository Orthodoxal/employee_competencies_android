package `is`.ulstu.myapplication.data.user.sources.api.entities

import com.squareup.moshi.JsonClass
import `is`.ulstu.myapplication.base.api.ResponseStatus

@JsonClass(generateAdapter = true)
data class RemoveActiveUserResponseEntity(
    val status: ResponseStatus
)
