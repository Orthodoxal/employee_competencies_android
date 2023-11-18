package `is`.ulstu.myapplication.data.employees.sources.cache.converters

import androidx.room.TypeConverter
import `is`.ulstu.myapplication.data.employees.sources.cache.entities.RatedValueEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RatedValueConverter {

    @TypeConverter
    fun fromRatedValue(value: RatedValueEntity) = Json.encodeToString(value)

    @TypeConverter
    fun toRatedValue(value: String) = Json.decodeFromString<RatedValueEntity>(value)
}
