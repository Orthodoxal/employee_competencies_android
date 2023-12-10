package `is`.ulstu.myapplication.features.employees.domain.models

import kotlinx.datetime.LocalDate
import java.math.BigDecimal

data class EmployeeModel(
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
    val rate: EmployeeRateModel,
)
