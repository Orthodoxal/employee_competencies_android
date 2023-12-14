package `is`.ulstu.myapplication.features.filter.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.features.filter.domain.models.AgeFilter
import `is`.ulstu.myapplication.features.filter.domain.models.DepartmentFilter
import `is`.ulstu.myapplication.features.filter.domain.models.FilterAction
import `is`.ulstu.myapplication.features.filter.domain.models.PositionFilter
import `is`.ulstu.myapplication.features.filter.domain.models.SalaryFilter
import `is`.ulstu.myapplication.features.filter.domain.models.SeniorityFilter
import `is`.ulstu.myapplication.features.filter.domain.models.TechsFilter
import `is`.ulstu.myapplication.ui.theme.BadColor
import `is`.ulstu.myapplication.ui.theme.Typography
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
internal fun EmployeesScreen(model: FilterViewModel = hiltViewModel()) {
    val state by model.state.collectAsState()



    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val onEmployeeItemClick: (employeeId: Long?) -> Unit = remember {
            { model.onEmployeeItemClick(employeeId = it) }
        }

        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter),
            contentPadding = PaddingValues(bottom = 60.dp),
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = "Расширенный поиск сотрудников",
                    style = Typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                )
            }

            items(state.filteredCatalog) { employee ->
                EmployeeItem(
                    employee = employee,
                    onClick = onEmployeeItemClick,
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            onClick = model::showFilterConfig,
        ) {
            Text("Искать")
        }

        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        if (state.filterConfigState is FilterConfigState.Show) {
            ModalBottomSheet(
                onDismissRequest = model::closeFilterConfig,
                sheetState = sheetState,
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(vertical = 12.dp),
                ) {
                    items(state.filterActions) { filter ->
                        when (filter) {
                            is SalaryFilter     -> SalaryFilter(filter, onFilterChange = model::onFilterChange)
                            is SeniorityFilter  -> SeniorityFilter(filter, onFilterChange = model::onFilterChange)
                            is AgeFilter        -> AgeFilter(filter, onFilterChange = model::onFilterChange)
                            is DepartmentFilter -> DepartmentFilter(filter, onFilterChange = model::onFilterChange)
                            is PositionFilter   -> PositionFilter(filter, onFilterChange = model::onFilterChange)
                            is TechsFilter      -> TechsFilter(filter, onFilterChange = model::onFilterChange)
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                modifier = Modifier.defaultMinSize(minWidth = 150.dp),
                                onClick = model::filterReset,
                                colors = ButtonDefaults.buttonColors(containerColor = BadColor)
                            ) {
                                Text("Сбросить")
                            }

                            Button(
                                modifier = Modifier.defaultMinSize(minWidth = 150.dp),
                                onClick = model::filter,
                            ) {
                                Text("Найти")
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
private fun EmployeeItem(
    employee: EmployeeModel,
    onClick: (employeeId: Long?) -> Unit,
) = Row(
    modifier = Modifier
        .padding(vertical = 8.dp, horizontal = 12.dp)
        .clickable(onClick = { onClick(employee.id) }),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Column(modifier = Modifier.weight(2.0f)) {
        Name(name = employee.fullName)

        Position(position = employee.position)

        Department(department = employee.department)
    }

    Column(
        modifier = Modifier.weight(1.0f),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
    ) {
        BirthDate(birthDate = employee.birthDate.toString())

        City(city = employee.city)
    }
}

@Composable
private fun Name(name: String) =
    Text(
        text = name,
        style = Typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
    )

@Composable
private fun Position(position: String) =
    Text(
        text = position,
        style = Typography.bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
    )

@Composable
private fun Department(department: String) =
    Text(
        text = department,
        style = Typography.bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
    )

@Composable
private fun BirthDate(birthDate: String) =
    Text(
        text = birthDate,
        style = Typography.bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
    )

@Composable
private fun City(city: String) =
    Text(
        text = city,
        style = Typography.bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
    )

@Composable
private fun SalaryFilter(filter: SalaryFilter, onFilterChange: (FilterAction) -> Unit) {
    Text(
        text = filter.label,
        style = Typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
    )

    var range by remember { mutableStateOf(filter.range) }
    RangeSlider(
        modifier = Modifier.padding(horizontal = 20.dp),
        value = range,
        onValueChange = {
            range = it
        },
        onValueChangeFinished = {
            onFilterChange(filter.copy(range = range))
        },
        valueRange = filter.rangeValues,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "От: ${range.start.roundToInt()}")
        Text(text = "До: ${range.endInclusive.roundToInt()}")
    }
}

@Composable
private fun SeniorityFilter(filter: SeniorityFilter, onFilterChange: (FilterAction) -> Unit) {
    Text(
        text = filter.label,
        style = Typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
    )

    var range by remember { mutableStateOf(filter.range.first.toFloat()..filter.range.last.toFloat()) }
    RangeSlider(
        modifier = Modifier.padding(horizontal = 20.dp),
        value = range,
        onValueChange = {
            range = it
        },
        onValueChangeFinished = {
            onFilterChange(filter.copy(range = range.start.toInt()..range.endInclusive.toInt()))
        },
        valueRange = filter.rangeValues,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "От: ${range.start.roundToInt()}")
        Text(text = "До: ${range.endInclusive.roundToInt()}")
    }
}

@Composable
private fun AgeFilter(filter: AgeFilter, onFilterChange: (FilterAction) -> Unit) {
    Text(
        text = filter.label,
        style = Typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
    )

    var range by remember { mutableStateOf(filter.range.first.toFloat()..filter.range.last.toFloat()) }
    RangeSlider(
        modifier = Modifier.padding(horizontal = 20.dp),
        value = range,
        onValueChange = {
            range = it
        },
        onValueChangeFinished = {
            onFilterChange(filter.copy(range = range.start.toInt()..range.endInclusive.toInt()))
        },
        valueRange = filter.rangeValues,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "От: ${range.start.roundToInt()}")
        Text(text = "До: ${range.endInclusive.roundToInt()}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DepartmentFilter(filter: DepartmentFilter, onFilterChange: (FilterAction) -> Unit) {
    Text(
        text = filter.label,
        style = Typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Выберите отдел") }
    ExposedDropdownMenuBox(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            filter.values.forEach {
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = remember {
                        {
                            selectedText = it
                            expanded = false
                            onFilterChange(filter.copy(departments = listOf(it)))
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PositionFilter(filter: PositionFilter, onFilterChange: (FilterAction) -> Unit) {
    Text(
        text = filter.label,
        style = Typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Выберите должность") }
    ExposedDropdownMenuBox(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            filter.values.forEach {
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = remember {
                        {
                            selectedText = it
                            expanded = false
                            onFilterChange(filter.copy(positions = listOf(it)))
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TechsFilter(filter: TechsFilter, onFilterChange: (FilterAction) -> Unit) {
    Text(
        text = filter.label,
        style = Typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Выберите технологию") }
    ExposedDropdownMenuBox(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            filter.values.forEach {
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = remember {
                        {
                            selectedText = it
                            expanded = false
                            onFilterChange(filter.copy(techs = listOf(it)))
                        }
                    }
                )
            }
        }
    }
}
