package `is`.ulstu.myapplication.data.employees.sources.cache.entities

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Serializable
data class EmployeeRateEntity(
    val teamWork: RatedValueEntity?,
    val leadership: RatedValueEntity?,
    val responsibility: RatedValueEntity?,
    val employeesRate: RatedValueEntity?,
    val clientsRate: RatedValueEntity?,
    val bossRate: RatedValueEntity?,
    val organizationWorkProcess: RatedValueEntity?,
    val punctuality: RatedValueEntity?,
    val stressResistance: RatedValueEntity?,
    val creativity: RatedValueEntity?,
    val analyticsSkills: RatedValueEntity?,
    val communicationsSkills: RatedValueEntity?,
)
