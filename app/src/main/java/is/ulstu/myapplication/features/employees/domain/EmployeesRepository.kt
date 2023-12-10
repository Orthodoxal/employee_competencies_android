package `is`.ulstu.myapplication.features.employees.domain

import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel

interface EmployeesRepository {
    suspend fun loadEmployeesCatalog(): List<EmployeeModel>
    suspend fun getEmployeesCatalogFromCache(): List<EmployeeModel>
    suspend fun setEmployeesCatalogToCache(employeesCatalog: List<EmployeeModel>)
    suspend fun findEmployeeByName(fullName: String): List<EmployeeModel>
}
