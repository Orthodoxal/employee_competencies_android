package `is`.ulstu.myapplication.features.employees.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `is`.ulstu.myapplication.base.AppException
import `is`.ulstu.myapplication.features.employee_info.presentation.EMPLOYEE_INFO_ROUTE
import `is`.ulstu.myapplication.features.employees.domain.use_cases.DownloadCatalogUseCase
import `is`.ulstu.myapplication.features.employees.domain.use_cases.FindEmployeeByNameUseCase
import `is`.ulstu.myapplication.features.employees.domain.use_cases.GetCatalogUseCase
import `is`.ulstu.myapplication.ui.navigation.NavigationIntent
import `is`.ulstu.myapplication.ui.navigation.Navigator
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val downloadCatalogUseCase: DownloadCatalogUseCase,
    private val getCatalogUseCase: GetCatalogUseCase,
    private val findEmployeeByNameUseCase: FindEmployeeByNameUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow(EmployeesScreenState())
    val state = _state.asStateFlow()

    private val _searchTextFlow = MutableSharedFlow<String>(extraBufferCapacity = 5)

    private val handler = CoroutineExceptionHandler { _, throwable ->
        val message = when (throwable) {
            is AppException -> throwable.message
            else            -> "Неизвестная ошибка"
        }
        _state.update { it.copy(refreshButtonState = RefreshButtonState.None, error = EmployeesError.Error(message = message)) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.update { it.copy(refreshButtonState = RefreshButtonState.Loading) }
            val catalog = getCatalogUseCase().let {
                when (it.isEmpty()) {
                    true -> downloadCatalogUseCase()
                    else -> it
                }
            }
            _state.update {
                it.copy(catalog = catalog, refreshButtonState = RefreshButtonState.Loading, error = EmployeesError.None)
            }
        }

        _searchTextFlow
            .distinctUntilChanged()
            .debounce(SEARCH_DEBOUNCE)
            .onEach { searchEmployeesByName(it) }
            .launchIn(viewModelScope)
    }

    fun onRefreshClick() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.update { it.copy(refreshButtonState = RefreshButtonState.Loading) }
            val catalog = downloadCatalogUseCase()
            _state.update {
                it.copy(catalog = catalog, refreshButtonState = RefreshButtonState.Loading, error = EmployeesError.None)
            }
        }
    }

    fun onSearchTextChange(searchText: String) {
        _state.update { it.copy(searchText = searchText) }
        _searchTextFlow.tryEmit(searchText)
    }

    fun onEmployeeItemClick(employeeId: Long?) =
        navigator.navigateTo(NavigationIntent.NavigationTo(route = "$EMPLOYEE_INFO_ROUTE/${employeeId ?: ""}"))

    private suspend fun searchEmployeesByName(fullName: String) {
        val filteredCatalog = findEmployeeByNameUseCase(fullName)
        _state.update { it.copy(catalog = filteredCatalog) }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 500L
    }
}
