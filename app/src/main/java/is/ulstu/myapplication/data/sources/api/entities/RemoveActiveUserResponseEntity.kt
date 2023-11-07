package `is`.ulstu.myapplication.data.sources.api.entities

import com.squareup.moshi.JsonClass
import `is`.ulstu.myapplication.base.api.ResponseStatus

@JsonClass(generateAdapter = true)
data class RemoveActiveUserResponseEntity(
    val status: ResponseStatus
)
