package `is`.ulstu.myapplication.data.employees.sources.api

import `is`.ulstu.myapplication.data.employees.sources.api.entities.EmployeeResponseEntity

interface EmployeesSource {
    suspend fun getEmployeesCatalog(): List<EmployeeResponseEntity>
}
