package `is`.ulstu.myapplication.features.filter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `is`.ulstu.myapplication.base.AppException
import `is`.ulstu.myapplication.features.employee_info.presentation.EMPLOYEE_INFO_ROUTE
import `is`.ulstu.myapplication.features.filter.domain.use_cases.FilterEmployeesUseCase
import `is`.ulstu.myapplication.features.filter.domain.use_cases.LoadInitialFilterValuesUseCase
import `is`.ulstu.myapplication.ui.navigation.NavigationIntent
import `is`.ulstu.myapplication.ui.navigation.Navigator
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val filterEmployeesUseCase: FilterEmployeesUseCase,
    private val loadInitialFilterValuesUseCase: LoadInitialFilterValuesUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow(FilterScreenState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        val message = when (throwable) {
            is AppException -> throwable.message
            else            -> "Неизвестная ошибка"
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val initialFilters = loadInitialFilterValuesUseCase()
            val filteredCatalog = filterEmployeesUseCase(initialFilters)
            _state.update {
                it.copy(
                    filteredCatalog = filteredCatalog,
                    filterActions = initialFilters,
                )
            }
        }
    }

    fun onEmployeeItemClick(employeeId: Long?) =
        navigator.navigateTo(NavigationIntent.NavigationTo(route = "$EMPLOYEE_INFO_ROUTE/${employeeId ?: ""}"))

    fun filter() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val filtered = filterEmployeesUseCase(state.value.filterActions)
            _state.update { it.copy(filteredCatalog = filtered) }
        }
    }
}
