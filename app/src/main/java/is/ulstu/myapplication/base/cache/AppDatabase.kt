package `is`.ulstu.myapplication.base.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import `is`.ulstu.myapplication.data.employees.sources.cache.EmployeesDao
import `is`.ulstu.myapplication.data.employees.sources.cache.converters.EmployeeRateConverter
import `is`.ulstu.myapplication.data.employees.sources.cache.converters.RatedValueConverter
import `is`.ulstu.myapplication.data.employees.sources.cache.entities.EmployeeEntity

@Database(
    version = 1,
    entities = [
        EmployeeEntity::class
    ]
)
@TypeConverters(StringListConverter::class, EmployeeRateConverter::class, RatedValueConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeesDao(): EmployeesDao
}
