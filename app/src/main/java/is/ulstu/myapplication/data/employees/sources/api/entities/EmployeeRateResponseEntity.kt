package `is`.ulstu.myapplication.data.employees.sources.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeRateResponseEntity(
    val teamWork: RatedValueResponseEntity?,
    val leadership: RatedValueResponseEntity?,
    val responsibility: RatedValueResponseEntity?,
    val employeesRate: RatedValueResponseEntity?,
    val clientsRate: RatedValueResponseEntity?,
    val bossRate: RatedValueResponseEntity?,
    val organizationWorkProcess: RatedValueResponseEntity?,
    val punctuality: RatedValueResponseEntity?,
    val stressResistance: RatedValueResponseEntity?,
    val creativity: RatedValueResponseEntity?,
    val analyticsSkills: RatedValueResponseEntity?,
    val communicationsSkills: RatedValueResponseEntity?,
)
