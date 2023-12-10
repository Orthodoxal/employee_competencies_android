package `is`.ulstu.myapplication.data.employees.sources.api.entities

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class EmployeeResponseEntity(
    val id: Long,
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
    val rate: EmployeeRateResponseEntity,
)
