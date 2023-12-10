package `is`.ulstu.myapplication.features.employees.domain.models

data class EmployeeModel(
    val id: Long? = null,
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
    val rate: EmployeeRateModel,
)
