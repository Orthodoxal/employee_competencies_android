package `is`.ulstu.myapplication.data.employees

import `is`.ulstu.myapplication.data.employees.sources.api.EmployeesSource
import `is`.ulstu.myapplication.data.employees.sources.cache.EmployeesDao
import `is`.ulstu.myapplication.features.employee_info.domain.EmployeeInfoRepository
import `is`.ulstu.myapplication.features.employees.domain.EmployeesRepository
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeesRepositoryImpl @Inject constructor(
    private val employeesSource: EmployeesSource,
    private val employeesMapper: EmployeesMapper,
    private val employeesDao: EmployeesDao,
) : EmployeesRepository, EmployeeInfoRepository {

    override suspend fun loadEmployeesCatalog(): List<EmployeeModel> =
        employeesMapper.run {
            employeesSource.getEmployeesCatalog().map { it.toEmployeeModel() }
        }

    override suspend fun getEmployeesCatalogFromCache(): List<EmployeeModel> =
        employeesMapper.run { employeesDao.getEmployeesCatalog().map { it.toEmployeeModel() } }

    override suspend fun setEmployeesCatalogToCache(employeesCatalog: List<EmployeeModel>) =
        employeesMapper.run {
            employeesDao.setEmployeesCatalog(employeesCatalog = employeesCatalog.map { it.toEmployeeEntity() })
        }

    override suspend fun getEmployeeById(employeeId: Long): EmployeeModel =
        employeesMapper.run { employeesDao.getEmployeeById(employeeId).toEmployeeModel() }

    override suspend fun findEmployeeByName(fullName: String): List<EmployeeModel> =
        employeesMapper.run {
            val wrappedFullName = "%$fullName%"
            employeesDao.findEmployeeByName(wrappedFullName).map { it.toEmployeeModel() }
        }
}
