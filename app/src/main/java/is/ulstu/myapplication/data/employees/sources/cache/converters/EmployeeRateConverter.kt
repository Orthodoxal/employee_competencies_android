package `is`.ulstu.myapplication.data.employees.sources.cache.converters

import androidx.room.TypeConverter
import `is`.ulstu.myapplication.data.employees.sources.cache.entities.EmployeeRateEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EmployeeRateConverter {

    @TypeConverter
    fun fromEmployeeRate(value: EmployeeRateEntity) = Json.encodeToString(value)

    @TypeConverter
    fun toEmployeeRate(value: String) = Json.decodeFromString<EmployeeRateEntity>(value)
}
