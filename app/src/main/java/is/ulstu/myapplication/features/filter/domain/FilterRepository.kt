package `is`.ulstu.myapplication.features.filter.domain

import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.features.filter.domain.models.FilterAction

interface FilterRepository {
    suspend fun loadFilterLimits(): List<FilterAction>
    suspend fun filterEmployees(filters: List<FilterAction>): List<EmployeeModel>
}