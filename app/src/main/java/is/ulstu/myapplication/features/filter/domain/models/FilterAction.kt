package `is`.ulstu.myapplication.features.filter.domain.models

import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.math.BigDecimal

sealed interface FilterAction {
    fun filter(source: Sequence<EmployeeModel>): Sequence<EmployeeModel>
}

data class SalaryFilter(val from: BigDecimal, val to: BigDecimal) : FilterAction {
    override fun filter(source: Sequence<EmployeeModel>) = source.filter { it.salary in from..to }
}

data class SeniorityFilter(val from: Int, val to: Int) : FilterAction {
    override fun filter(source: Sequence<EmployeeModel>) = source.filter { it.seniority in from..to }
}

data class AgeFilter(val from: Int, val to: Int) : FilterAction {
    override fun filter(source: Sequence<EmployeeModel>): Sequence<EmployeeModel> {
        val currentMoment: Instant = Clock.System.now()
        val currentDate = currentMoment.toLocalDateTime(TimeZone.UTC).date
        return source.filter {
            it.birthDate.year in (currentDate.year - to)..(currentDate.year - from)
        }
    }
}

data class DepartmentFilter(val departments: List<String>) : FilterAction {
    override fun filter(source: Sequence<EmployeeModel>) =
        source.filter { departments.contains(it.department) }
}

data class PositionFilter(val positions: List<String>) : FilterAction {
    override fun filter(source: Sequence<EmployeeModel>) =
        source.filter { positions.contains(it.position) }
}

data class TechsFilter(val techs: List<String>) : FilterAction {
    override fun filter(source: Sequence<EmployeeModel>) =
        source.filter { employeeModel -> employeeModel.techs.any { techs.contains(it) } }
}
