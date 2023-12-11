package `is`.ulstu.myapplication.features.filter.presentation

import androidx.compose.runtime.Immutable
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.features.filter.domain.models.FilterAction

@Immutable
data class FilterScreenState(
    val filteredCatalog: List<EmployeeModel> = emptyList(),
    val filterActions: List<FilterAction> = emptyList(),
    val filterConfigState: FilterConfigState = FilterConfigState.None,
)

sealed interface FilterConfigState {
    object None : FilterConfigState
    object Show : FilterConfigState
}
