package `is`.ulstu.myapplication.features.filter.domain.models

import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

sealed interface FilterAction {
    val label: String

    fun filter(source: Sequence<EmployeeModel>): Sequence<EmployeeModel>
}

interface RangeFilterType {
    val rangeValues: ClosedFloatingPointRange<Float>

    companion object {
        val DEFAULT = 0f..1f
    }
}

interface ListFilterType<T> {
    val values: List<T>

    companion object {
        fun <T> default() = emptyList<T>()
    }
}

data class SalaryFilter(
    val range: ClosedFloatingPointRange<Float>,
    override val label: String = "Заработная плата",
    override val rangeValues: ClosedFloatingPointRange<Float> = RangeFilterType.DEFAULT,
) : FilterAction, RangeFilterType {

    override fun filter(source: Sequence<EmployeeModel>) = source.filter { it.salary.toFloat() in range }
}

data class SeniorityFilter(
    val range: IntRange,
    override val label: String = "Стаж в компании",
    override val rangeValues: ClosedFloatingPointRange<Float> = RangeFilterType.DEFAULT,
) : FilterAction, RangeFilterType {

    override fun filter(source: Sequence<EmployeeModel>) = source.filter { it.seniority in range }
}

data class AgeFilter(
    val range: IntRange,
    override val label: String = "Возраст",
    override val rangeValues: ClosedFloatingPointRange<Float> = RangeFilterType.DEFAULT,
) : FilterAction, RangeFilterType {

    override fun filter(source: Sequence<EmployeeModel>): Sequence<EmployeeModel> {
        val currentMoment: Instant = Clock.System.now()
        val currentDate = currentMoment.toLocalDateTime(TimeZone.UTC).date
        return source.filter {
            it.birthDate.year in (currentDate.year - range.last)..(currentDate.year - range.first)
        }
    }
}

data class DepartmentFilter(
    val departments: List<String>,
    override val label: String = "Отдел",
    override val values: List<String> = ListFilterType.default(),
) : FilterAction, ListFilterType<String> {

    override fun filter(source: Sequence<EmployeeModel>) =
        source.filter { departments.contains(it.department) }
}

data class PositionFilter(
    val positions: List<String>,
    override val label: String = "Должность",
    override val values: List<String> = ListFilterType.default(),
) : FilterAction, ListFilterType<String> {

    override fun filter(source: Sequence<EmployeeModel>) =
        source.filter { positions.contains(it.position) }
}

data class TechsFilter(
    val techs: List<String>,
    override val label: String = "Технологии",
    override val values: List<String> = ListFilterType.default(),
) : FilterAction, ListFilterType<String> {

    override fun filter(source: Sequence<EmployeeModel>) =
        source.filter { employeeModel -> employeeModel.techs.any { techs.contains(it) } }
}
