package `is`.ulstu.myapplication.features.profile.domain.use_cases

import `is`.ulstu.myapplication.data.repository.UserRepositoryTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SignOutUseCaseTest {
    private lateinit var userRepositoryTest: UserRepositoryTest
    private lateinit var signOutUseCase: SignOutUseCase

    @Before
    fun setUp() {
        userRepositoryTest = UserRepositoryTest()
        signOutUseCase = SignOutUseCase(userRepositoryTest)
        runBlocking {
            val userInfoModel = userRepositoryTest.authorizeUser(UserRepositoryTest.USER_LOGIN, UserRepositoryTest.USER_PASSWORD)
            userRepositoryTest.saveUserInfoToCache(userInfoModel)
        }
    }

    @Test
    operator fun invoke() = runBlocking {
        signOutUseCase()
        val user = userRepositoryTest.getUserInfoFromCache()
        assert(user == null)
    }
}
