package `is`.ulstu.myapplication.base.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponseBody(
    val message: String
)
