package `is`.ulstu.myapplication.features.employees.domain.use_cases

import `is`.ulstu.myapplication.features.employees.domain.EmployeesRepository
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadCatalogUseCase @Inject constructor(
    private val employeesRepository: EmployeesRepository,
) {

    suspend operator fun invoke(): List<EmployeeModel> {
        val catalog = employeesRepository.loadEmployeesCatalog()
        employeesRepository.setEmployeesCatalogToCache(catalog)
        return employeesRepository.getEmployeesCatalogFromCache()
    }
}
