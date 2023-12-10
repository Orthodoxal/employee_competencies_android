package `is`.ulstu.myapplication.features.employee_info.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `is`.ulstu.myapplication.base.AppException
import `is`.ulstu.myapplication.features.employee_info.domain.use_cases.GetEmployeeInfoUseCase
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EmployeeInfoViewModel @Inject constructor(
    private val getEmployeeInfoUseCase: GetEmployeeInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(EmployeeInfoScreenState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        val message = when (throwable) {
            is AppException -> throwable.message
            else            -> "Неизвестная ошибка"
        }
        _state.update { EmployeeInfoScreenState() }
    }

    fun loadEmployeeInfo(id: Long?) {
        if (id == null) return

        viewModelScope.launch(Dispatchers.IO + handler) {
            val employee = getEmployeeInfoUseCase(id)
            val rateState = if (isEmployeeRateExist(employee)) RateState.Hidden else RateState.None
            _state.update { it.copy(employee = employee, rateState = rateState) }
        }
    }

    fun onHiddenHiddenDescriptionVisibilityChanged(hiddenDescription: HiddenDescriptions) =
        _state.update {
            it.copy(
                hiddenDescriptions =
                it.hiddenDescriptions + (hiddenDescription to !(it.hiddenDescriptions[hiddenDescription] ?: false))
            )
        }

    fun onHeaderRateVisibilityChanged() =
        _state.update {
            it.copy(
                rateState = when (it.rateState) {
                    RateState.None   -> RateState.None
                    RateState.Hidden -> RateState.Show
                    RateState.Show -> RateState.Hidden
                }
            )
        }
}

private fun isEmployeeRateExist(employeeModel: EmployeeModel) = with(employeeModel.rate) {
    teamWork != null || leadership != null || responsibility != null || employeesRate != null ||
    clientsRate != null || bossRate != null || organizationWorkProcess != null || punctuality != null ||
    stressResistance != null || creativity != null || analyticsSkills != null || communicationsSkills != null
}
