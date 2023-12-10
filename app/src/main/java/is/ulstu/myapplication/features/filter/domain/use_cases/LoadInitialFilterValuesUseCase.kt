package `is`.ulstu.myapplication.features.filter.domain.use_cases

import `is`.ulstu.myapplication.features.employees.domain.EmployeesRepository
import `is`.ulstu.myapplication.features.filter.domain.models.AgeFilter
import `is`.ulstu.myapplication.features.filter.domain.models.DepartmentFilter
import `is`.ulstu.myapplication.features.filter.domain.models.FilterAction
import `is`.ulstu.myapplication.features.filter.domain.models.PositionFilter
import `is`.ulstu.myapplication.features.filter.domain.models.SalaryFilter
import `is`.ulstu.myapplication.features.filter.domain.models.SeniorityFilter
import `is`.ulstu.myapplication.features.filter.domain.models.TechsFilter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadInitialFilterValuesUseCase @Inject constructor(
    private val employeesRepository: EmployeesRepository,
) {

    suspend operator fun invoke(): List<FilterAction> {
        val catalog = employeesRepository.getEmployeesCatalogFromCache().asSequence()
        return buildList {
            add(
                SalaryFilter(
                    from = catalog.minOf { it.salary },
                    to = catalog.maxOf { it.salary },
                )
            )
            add(
                SeniorityFilter(
                    from = catalog.minOf { it.seniority },
                    to = catalog.maxOf { it.seniority },
                )
            )
            val currentMoment: Instant = Clock.System.now()
            val currentDate = currentMoment.toLocalDateTime(TimeZone.UTC).date
            add(
                AgeFilter(
                    from = catalog.minOf { (currentDate - it.birthDate).years },
                    to = catalog.maxOf { (currentDate - it.birthDate).years },
                )
            )
            add(
                DepartmentFilter(
                    departments = catalog.map { it.department }.distinct().toList()
                )
            )
            add(
                PositionFilter(
                    positions = catalog.map { it.position }.distinct().toList()
                )
            )
            add(
                TechsFilter(
                    techs = catalog.flatMap { it.techs.asSequence() }.distinct().toList()
                )
            )
        }
    }
}
