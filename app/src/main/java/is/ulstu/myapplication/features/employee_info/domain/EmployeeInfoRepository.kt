package `is`.ulstu.myapplication.features.employee_info.domain

import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel

interface EmployeeInfoRepository {
    suspend fun getEmployeeById(employeeId: Long): EmployeeModel
}
