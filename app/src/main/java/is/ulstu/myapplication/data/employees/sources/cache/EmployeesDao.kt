package `is`.ulstu.myapplication.data.employees.sources.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import `is`.ulstu.myapplication.data.employees.sources.cache.entities.EmployeeEntity

@Dao
interface EmployeesDao {

    @Query("SELECT * FROM employees_catalog ORDER BY fullName")
    fun getEmployeesCatalog(): List<EmployeeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setEmployeesCatalog(employeesCatalog: List<EmployeeEntity>)

    @Query("SELECT * FROM employees_catalog WHERE id = :employeeId")
    suspend fun getEmployeeById(employeeId: Long): EmployeeEntity

    @Query("SELECT * FROM employees_catalog WHERE fullName LIKE :fullName")
    suspend fun findEmployeeByName(fullName: String): List<EmployeeEntity>
}
