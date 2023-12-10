package `is`.ulstu.myapplication.features.filter.domain.models

import java.math.BigDecimal

sealed interface FilterAction {
    data class SalaryFilter(val from: BigDecimal, val to: BigDecimal)
    data class SeniorityFilter(val from: Int, val to: Int)
    data class AgeFilter(val from: Int, val to: Int)

    data class DepartmentFilter(val departments: List<String>)
    data class PositionFilter(val positions: List<String>)
    data class TechsFilter(val techs: List<String>)
}