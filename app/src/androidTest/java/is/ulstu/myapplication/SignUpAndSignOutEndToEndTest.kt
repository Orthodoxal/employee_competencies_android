package `is`.ulstu.myapplication

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import `is`.ulstu.myapplication.base.cache.CacheModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CacheModule::class)
class SignUpAndSignOutEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun start() {
        signUpTest()
        signOutTest()
    }

    private fun signUpTest() {
        composeRule.onNodeWithText("Логин").performTextInput("ivanov")
        composeRule.onNodeWithText("Пароль").performTextInput("123456")
        composeRule.onNodeWithText("Войти").assertExists().performClick()
        composeRule.onNodeWithText("Профиль").assertExists()
    }

    private fun signOutTest() {
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