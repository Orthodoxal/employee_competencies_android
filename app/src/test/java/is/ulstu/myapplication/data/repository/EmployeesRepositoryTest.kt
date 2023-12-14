package `is`.ulstu.myapplication.data.repository

import `is`.ulstu.myapplication.features.employee_info.domain.EmployeeInfoRepository
import `is`.ulstu.myapplication.features.employees.domain.EmployeesRepository
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeRateModel
import kotlinx.datetime.LocalDate
import java.math.BigDecimal

class EmployeesRepositoryTest : EmployeesRepository, EmployeeInfoRepository {
    private var apiEmployees: List<EmployeeModel> = generateApiCatalog()
    private var cacheEmployees: List<EmployeeModel> = emptyList()

    override suspend fun loadEmployeesCatalog(): List<EmployeeModel> = apiEmployees

    override suspend fun getEmployeesCatalogFromCache(): List<EmployeeModel> = cacheEmployees

    override suspend fun setEmployeesCatalogToCache(employeesCatalog: List<EmployeeModel>) {
        cacheEmployees = employeesCatalog
    }

    override suspend fun getEmployeeById(employeeId: Long): EmployeeModel =
        cacheEmployees.first { it.id == employeeId }

    override suspend fun findEmployeeByName(fullName: String): List<EmployeeModel> =
        cacheEmployees.filter { it.fullName.contains(fullName) }

    private fun generateApiCatalog() = buildList {
        for (id in 0..10) {
            add(
                EmployeeModel(
                    id = id.toLong(),
                    fullName = when (id % 3) {
                        0    -> "test0${id / 3}"
                        1    -> "test1${id / 3}"
                        2    -> "test2${id / 3}"
                        else -> "test"
                    },
                    city = "test",
                    birthDate = LocalDate(1980, 10, 10),
                    department = "test",
                    position = "test",
                    seniority = 1000,
                    hardSkills = listOf("test"),
                    softSkills = listOf("test"),
                    techs = listOf("test"),
                    hrRecommendations = listOf("test"),
                    achievements = listOf("test"),
                    salary = BigDecimal(100000 * (id % 3 + 1)),
                    rate = EmployeeRateModel(
                        teamWork = null,
                        leadership = null,
                        responsibility = null,
                        employeesRate = null,
                        clientsRate = null,
                        bossRate = null,
                        organizationWorkProcess = null,
                        punctuality = null,
                        stressResistance = null,
                        creativity = null,
                        analyticsSkills = null,
                        communicationsSkills = null,
                    ),
                )
            )
        }
    }.shuffled()
}
