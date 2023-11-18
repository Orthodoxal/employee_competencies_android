package `is`.ulstu.myapplication.employees.presentation

import androidx.compose.runtime.Immutable
import `is`.ulstu.myapplication.employees.domain.models.EmployeeModel

@Immutable
data class EmployeesScreenState(
    val catalog: List<EmployeeModel> = emptyList(),
    val searchText: String = "",
    val refreshButtonState: RefreshButtonState = RefreshButtonState.None,
    val error: EmployeesError = EmployeesError.None,
)

sealed class RefreshButtonState {
    object None : RefreshButtonState()
    object Loading : RefreshButtonState()
}

sealed class EmployeesError {
    object None : EmployeesError()
    data class Error(val message: String) : EmployeesError()
}
