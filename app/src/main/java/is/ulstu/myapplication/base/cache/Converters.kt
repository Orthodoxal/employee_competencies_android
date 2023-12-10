package `is`.ulstu.myapplication.base.cache

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.math.BigDecimal
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate

class StringListConverter {

    @TypeConverter
    fun fromList(value: List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}

class BigDecimalDoubleTypeConverter {

    @TypeConverter
    fun bigDecimalToDouble(input: BigDecimal?): Double {
        return input?.toDouble() ?: 0.0
    }

    @TypeConverter
    fun stringToBigDecimal(input: Double?): BigDecimal {
        if (input == null) return BigDecimal.ZERO
        return BigDecimal.valueOf(input) ?: BigDecimal.ZERO
    }

}

class LocalDateConverter {

    @TypeConverter
    fun from(value: LocalDate) = value.toString()

    @TypeConverter
    fun to(value: String) = value.toLocalDate()
}
