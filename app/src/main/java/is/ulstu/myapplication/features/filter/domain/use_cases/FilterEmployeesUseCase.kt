package `is`.ulstu.myapplication.features.filter.domain.use_cases

import `is`.ulstu.myapplication.features.employees.domain.EmployeesRepository
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.features.filter.domain.models.FilterAction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterEmployeesUseCase @Inject constructor(
    private val employeesRepository: EmployeesRepository,
) {

    suspend operator fun invoke(filters: List<FilterAction>): List<EmployeeModel> {
        val catalog = employeesRepository.getEmployeesCatalogFromCache().asSequence()
        return filters
            .fold(catalog) { acc, filterAction ->
                filterAction.filter(acc)
            }
            .toList()
    }
}
