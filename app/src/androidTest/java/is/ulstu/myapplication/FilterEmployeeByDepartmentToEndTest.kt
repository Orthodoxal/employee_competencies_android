package `is`.ulstu.myapplication

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import `is`.ulstu.myapplication.base.cache.CacheModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CacheModule::class)
class FilterEmployeeByDepartmentToEndTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.onNodeWithText("Логин").performTextInput("ivanov")
        composeRule.onNodeWithText("Пароль").performTextInput("123456")
        composeRule.onNodeWithText("Войти").assertExists().performClick()
        composeRule.onNodeWithText("Профиль").assertExists()
    }

    @Test
    fun start() {
        onFilterEmployeeTest()
    }

    private fun onFilterEmployeeTest() {
        runBlocking { composeRule.awaitIdle() }
        runBlocking { delay(2000) }

        composeRule
            .onNodeWithText("Фильтр")
            .assertExists()
            .performClick()

        runBlocking { composeRule.awaitIdle() }
        runBlocking { delay(2000) }

        composeRule
            .onNodeWithText("Искать")
            .assertExists()
            .performClick()

        runBlocking { composeRule.awaitIdle() }

        runBlocking { delay(2000) }

        composeRule
            .onNodeWithText("Выберите должность")
            .assertExists()
            .performClick()

        runBlocking { composeRule.awaitIdle() }
        runBlocking { delay(2000) }

        composeRule
            .onAllNodesWithText("Старший разработчик")
            .onLast()
            .assertExists()
            .performClick()

        runBlocking { composeRule.awaitIdle() }
        runBlocking { delay(2000) }

        composeRule
            .onNodeWithText("Найти")
            .assertExists()
            .performClick()

        runBlocking { composeRule.awaitIdle() }
        runBlocking { delay(2000) }

        composeRule
            .onAllNodesWithText("Отдел", substring = true)
            .onFirst()
            .performClick()

        runBlocking { composeRule.awaitIdle() }
        runBlocking { delay(2000) }

        composeRule
            .onNodeWithText("Иванов Иван Иванович", substring = true)
            .assertExists()

        runBlocking { composeRule.awaitIdle() }
        runBlocking { delay(2000) }

        /*runBlocking { composeRule.awaitIdle() }

        composeRule
            .onAllNodesWithText("Петров Петр ", substring = true)
            .assertCountEquals(1)
            .onFirst()
            .assertExists()
            .performClick()

        runBlocking { composeRule.awaitIdle() }

        runBlocking { composeRule.awaitIdle() }

        composeRule
            .onNodeWithText("В компании:", substring = true)
            .assertExists()

        runBlocking { composeRule.awaitIdle() }*/
    }

    @After
    fun exit() {
        runBlocking { composeRule.awaitIdle() }

        runBlocking(Dispatchers.Main) { composeRule.activity.onBackPressedDispatcher.onBackPressed() }

        runBlocking { composeRule.awaitIdle() }

        composeRule
            .onNodeWithText("Профиль")
            .assertExists()
            .performClick()

        composeRule
            .onNodeWithText("Выйти")
            .assertExists()
            .performClick()

        runBlocking { delay(2000) }

        composeRule
            .onNodeWithText("Войти")
            .assertExists()

        runBlocking { delay(2000) }
    }
}