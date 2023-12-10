package `is`.ulstu.myapplication.features.employee_info.presentation

import androidx.compose.runtime.Immutable
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel

@Immutable
internal data class EmployeeInfoScreenState(
    val employee: EmployeeModel? = null,
    val hiddenDescriptions: Map<HiddenDescriptions, Boolean> = initialHiddenDescriptions(),
    val rateState: RateState = RateState.None,
)

internal fun initialHiddenDescriptions() = HiddenDescriptions.entries.associateWith { true }

enum class HiddenDescriptions(val header: String) {
    HARD_SKILLS(header = "Hard Skills"),
    SOFT_SKILLS(header = "Soft Skills"),
    TECHS(header = "Технологии"),
    HR_RECOMMENDATIONS(header = "HR рекоммендации"),
    ACHIEVEMENTS(header = "Достижения"),
}

internal sealed interface RateState {
    object None : RateState
    object Show : RateState
    object Hidden : RateState
}
