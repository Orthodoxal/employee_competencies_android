package `is`.ulstu.myapplication.features.filter.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.ui.theme.Typography


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
                    modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
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

        val remOnClick: () -> Unit = remember {
            {}
        }

        Button(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            onClick = remOnClick,
        ) {
            Text("Искать")
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
