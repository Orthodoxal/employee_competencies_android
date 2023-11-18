package `is`.ulstu.myapplication.data.employees.sources.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeResponseEntity(
    val id: Long,
    val fullName: String,
    val city: String,
    val birthDate: String,
    val department: String,
    val position: String,
    val seniority: String,
    val hardSkills: List<String>,
    val softSkills: List<String>,
    val techs: List<String>,
    val hrRecommendations: List<String>,
    val achievements: List<String>,
    val salary: String,
    val rate: EmployeeRateResponseEntity,
)
