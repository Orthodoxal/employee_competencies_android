package `is`.ulstu.myapplication.employees.domain.use_cases

import `is`.ulstu.myapplication.employees.domain.EmployeesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FindEmployeeByNameUseCase @Inject constructor(
    private val employeesRepository: EmployeesRepository,
) {

    suspend operator fun invoke(fullName: String) = employeesRepository.findEmployeeByName(fullName)
}
