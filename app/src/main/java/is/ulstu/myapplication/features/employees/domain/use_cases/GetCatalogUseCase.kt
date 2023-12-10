package `is`.ulstu.myapplication.features.employees.domain.use_cases

import `is`.ulstu.myapplication.features.employees.domain.EmployeesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCatalogUseCase @Inject constructor(
    private val employeesRepository: EmployeesRepository,
) {

    suspend operator fun invoke() = employeesRepository.getEmployeesCatalogFromCache()
}
