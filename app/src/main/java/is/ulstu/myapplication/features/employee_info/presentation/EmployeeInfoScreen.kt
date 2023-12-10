package `is`.ulstu.myapplication.features.employee_info.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import `is`.ulstu.myapplication.R
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeRateModel
import `is`.ulstu.myapplication.features.employees.domain.models.RateModel
import `is`.ulstu.myapplication.features.employees.domain.models.RatedValueModel
import `is`.ulstu.myapplication.ui.theme.AcceptableColor
import `is`.ulstu.myapplication.ui.theme.BadColor
import `is`.ulstu.myapplication.ui.theme.GoodColor
import `is`.ulstu.myapplication.ui.theme.NormalColor
import `is`.ulstu.myapplication.ui.theme.PerfectColor
import `is`.ulstu.myapplication.ui.theme.Typography

@Composable
internal fun EmployeeInfoScreen(
    employeeId: Long?,
    model: EmployeeInfoViewModel = hiltViewModel(),
) {
    val state by model.state.collectAsState()
    val scrollableState: ScrollState = rememberScrollState()

    LaunchedEffect(key1 = employeeId) {
        model.loadEmployeeInfo(employeeId)
    }

    state.employee?.let { employee ->
        Column(modifier = Modifier.verticalScroll(scrollableState)) {
            EmployeeMainInfo(employee)

            val onHeaderClick: (HiddenDescriptions) -> Unit = remember {
                { model.onHiddenHiddenDescriptionVisibilityChanged(it) }
            }

            HiddenDescriptions.values().forEach {
                HiddenDescriptionsField(
                    text = it.header,
                    items = getItemsByHiddenDescription(it, employee),
                    isHidden = state.hiddenDescriptions[it] ?: true,
                    onHeaderClick = { onHeaderClick(it) },
                )
            }

            val onHeaderRateClick: () -> Unit = remember {
                { model.onHeaderRateVisibilityChanged() }
            }

            if (state.rateState != RateState.None) {
                RateFields(
                    textStatistics = "Рейтинги сотрудника",
                    rate = employee.rate,
                    isHidden = state.rateState == RateState.Hidden,
                    onHeaderRateClick = onHeaderRateClick,
                )
            }
        }
    }
}

@Composable
private fun EmployeeMainInfo(
    employee: EmployeeModel,
) = Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
    Spacer(modifier = Modifier.size(20.dp))
    Name(name = employee.fullName)
    TextEmployeeField(text = "Город: ${employee.city}")
    TextEmployeeField(text = "Дата рождения: ${employee.birthDate}")

    Spacer(modifier = Modifier.size(20.dp))

    Name(name = "В компании: ")
    TextEmployeeField(text = "Подразделени: ${employee.department}")
    TextEmployeeField(text = "Должность: ${employee.position}")
    TextEmployeeField(text = "Стаж в компании: ${employee.seniority}")
    TextEmployeeField(text = "Заработная плата: ${employee.salary}")
}

@Composable
private fun Name(name: String) =
    Text(
        text = name,
        style = Typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
    )


@Composable
private fun TextEmployeeField(text: String) =
    Text(
        text = text,
        style = Typography.bodyLarge,
        color = MaterialTheme.colorScheme.secondary,
    )

@Composable
private fun HiddenDescriptionsField(
    text: String,
    items: List<String>,
    isHidden: Boolean,
    onHeaderClick: () -> Unit,
) = Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onHeaderClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = text,
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )

        Image(
            modifier = Modifier.size(16.dp),
            imageVector = ImageVector.vectorResource(
                id = if (isHidden) R.drawable.ic_selector_arrow_right else R.drawable.ic_selector_arrow_down
            ),
            contentDescription = null,
        )
    }

    if (!isHidden) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
        ) {
            items.forEachIndexed { index, item ->
                Text(
                    text = "${index + 1}. $item",
                    style = Typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}

@Composable
private fun RateFields(
    textStatistics: String,
    rate: EmployeeRateModel,
    isHidden: Boolean,
    onHeaderRateClick: () -> Unit,
) = Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onHeaderRateClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = textStatistics,
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )

        Image(
            modifier = Modifier.size(16.dp),
            imageVector = ImageVector.vectorResource(
                id = if (isHidden) R.drawable.ic_selector_arrow_right else R.drawable.ic_selector_arrow_down
            ),
            contentDescription = null,
        )
    }

    if (!isHidden) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            with(rate) {
                teamWork?.let { RateItem(name = "Работа в команде", ratedValue = it) }
                leadership?.let { RateItem(name = "Лидерство", ratedValue = it) }
                responsibility?.let { RateItem(name = "Ответственность", ratedValue = it) }
                employeesRate?.let { RateItem(name = "Оценка коллег", ratedValue = it) }
                clientsRate?.let { RateItem(name = "Оценка клиентов", ratedValue = it) }
                bossRate?.let { RateItem(name = "Оценка руководства", ratedValue = it) }
                organizationWorkProcess?.let { RateItem(name = "Организация рабочих процессов", ratedValue = it) }
                punctuality?.let { RateItem(name = "Пунктуальность", ratedValue = it) }
                stressResistance?.let { RateItem(name = "Стрессоустойчивость", ratedValue = it) }
                creativity?.let { RateItem(name = "Креативность", ratedValue = it) }
                analyticsSkills?.let { RateItem(name = "Аналитические навыки", ratedValue = it) }
                communicationsSkills?.let { RateItem(name = "Коммуникационные навыки", ratedValue = it) }
            }
        }
    }
}

@Composable
private fun RateItem(
    name: String,
    ratedValue: RatedValueModel,
) = Text(
    text = buildAnnotatedString {
        append("$name: ")

        withStyle(SpanStyle(color = colorByRate(ratedValue.rate))) {
            append(ratedValue.value)
        }
    },
    style = Typography.bodyLarge,
    color = MaterialTheme.colorScheme.secondary,
)

private fun colorByRate(rateModel: RateModel) =
    when (rateModel) {
        RateModel.PERFECT    -> PerfectColor
        RateModel.GOOD       -> GoodColor
        RateModel.NORMAL     -> NormalColor
        RateModel.ACCEPTABLE -> AcceptableColor
        RateModel.BAD        -> BadColor
    }

private fun getItemsByHiddenDescription(hiddenDescription: HiddenDescriptions, employee: EmployeeModel): List<String> =
    when (hiddenDescription) {
        HiddenDescriptions.HARD_SKILLS        -> employee.hardSkills
        HiddenDescriptions.SOFT_SKILLS        -> employee.softSkills
        HiddenDescriptions.TECHS              -> employee.techs
        HiddenDescriptions.HR_RECOMMENDATIONS -> employee.hrRecommendations
        HiddenDescriptions.ACHIEVEMENTS       -> employee.achievements
    }
