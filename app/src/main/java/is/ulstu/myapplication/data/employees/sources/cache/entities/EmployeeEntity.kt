package `is`.ulstu.myapplication.data.employees.sources.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import java.math.BigDecimal

@Entity(
    tableName = "employees_catalog"
)
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val fullName: String,
    val city: String,
    val birthDate: LocalDate,
    val department: String,
    val position: String,
    val seniority: Int,
    val hardSkills: List<String>,
    val softSkills: List<String>,
    val techs: List<String>,
    val hrRecommendations: List<String>,
    val achievements: List<String>,
    val salary: BigDecimal,
    val rate: EmployeeRateEntity,
)
