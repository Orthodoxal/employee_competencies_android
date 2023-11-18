package `is`.ulstu.myapplication.data.employees.sources.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatedValueResponseEntity(
    val value: String,
    val rate: RateResponseEntity,
)
