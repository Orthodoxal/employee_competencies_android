package `is`.ulstu.myapplication.features.employee_info.domain.use_cases

import `is`.ulstu.myapplication.features.employee_info.domain.EmployeeInfoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetEmployeeInfoUseCase @Inject constructor(
    private val employeeInfoRepository: EmployeeInfoRepository,
) {
    suspend operator fun invoke(id: Long) = employeeInfoRepository.getEmployeeById(id)
}
