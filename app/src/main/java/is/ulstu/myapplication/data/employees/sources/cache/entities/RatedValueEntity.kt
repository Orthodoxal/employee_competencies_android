package `is`.ulstu.myapplication.data.employees.sources.cache.entities

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Serializable
data class RatedValueEntity(
    val value: String,
    val rate: RateEntity,
)
