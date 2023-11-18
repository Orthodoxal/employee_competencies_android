package `is`.ulstu.myapplication.employees.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import `is`.ulstu.myapplication.R
import `is`.ulstu.myapplication.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
internal fun EmployeesScreen(model: EmployeesViewModel = hiltViewModel()) {
    val state by model.state.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(key1 = state.error) {
        (state.error as? EmployeesError.Error)?.message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                value = state.searchText,
                onValueChange = model::onSearchTextChange,
                placeholder = {
                    Text(
                        text = "Введите ФИО соотрудника",
                        style = Typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                },
                singleLine = true,
            )

            IconButton(
                modifier = Modifier.padding(end = 16.dp),
                onClick = model::onRefreshClick
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_refresh),
                    contentDescription = null,
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxSize()
        ) {
            items(state.catalog) { employee ->
                EmployeeItem(employee)
            }
        }
    }
}

@Composable
private fun EmployeeItem(
    employee: EmployeeModel,
) = Row(
    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
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
        BirthDate(birthDate = employee.birthDate)

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
