package `is`.ulstu.myapplication.base.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    entities = []
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

}
