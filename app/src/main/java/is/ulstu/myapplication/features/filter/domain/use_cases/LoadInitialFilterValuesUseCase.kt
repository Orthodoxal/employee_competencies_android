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
            val salary = catalog.minOf { it.salary }.toFloat()..catalog.maxOf { it.salary }.toFloat()
            add(
                SalaryFilter(
                    range = salary,
                    rangeValues = salary,
                )
            )

            val seniority = catalog.minOf { it.seniority }..catalog.maxOf { it.seniority }
            add(
                SeniorityFilter(
                    range = seniority,
                    rangeValues = seniority.first.toFloat()..seniority.last.toFloat(),
                )
            )

            val currentMoment: Instant = Clock.System.now()
            val currentDate = currentMoment.toLocalDateTime(TimeZone.UTC).date
            val age = catalog
                .minOf { (currentDate - it.birthDate).years }..catalog
                .maxOf { (currentDate - it.birthDate).years }
            add(
                AgeFilter(
                    range = age,
                    rangeValues = age.first.toFloat()..age.last.toFloat(),
                )
            )
            val departments = catalog.map { it.department }.distinct().toList()
            add(
                DepartmentFilter(
                    departments = departments,
                    values = departments,
                )
            )
            val positions = catalog.map { it.position }.distinct().toList()
            add(
                PositionFilter(
                    positions = positions,
                    values = positions,
                )
            )

            val techs = catalog.flatMap { it.techs.asSequence() }.distinct().toList()
            add(
                TechsFilter(
                    techs = techs,
                    values = techs,
                )
            )
        }
    }
}
