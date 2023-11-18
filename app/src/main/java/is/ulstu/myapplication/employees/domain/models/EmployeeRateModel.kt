package `is`.ulstu.myapplication.employees.domain.models

data class EmployeeRateModel(
    val teamWork: RatedValueModel?,
    val leadership: RatedValueModel?,
    val responsibility: RatedValueModel?,
    val employeesRate: RatedValueModel?,
    val clientsRate: RatedValueModel?,
    val bossRate: RatedValueModel?,
    val organizationWorkProcess: RatedValueModel?,
    val punctuality: RatedValueModel?,
    val stressResistance: RatedValueModel?,
    val creativity: RatedValueModel?,
    val analyticsSkills: RatedValueModel?,
    val communicationsSkills: RatedValueModel?,
)
