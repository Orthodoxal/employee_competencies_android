package `is`.ulstu.myapplication.features.profile.domain.use_cases

import `is`.ulstu.myapplication.data.repository.UserRepositoryTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoadUserInfoUseCaseTest {
    private lateinit var userRepositoryTest: UserRepositoryTest
    private lateinit var loadUserInfoUseCase: LoadUserInfoUseCase

    @Before
    fun setUp() {
        userRepositoryTest = UserRepositoryTest()
        loadUserInfoUseCase = LoadUserInfoUseCase(userRepositoryTest)
        runBlocking {
            val userInfoModel = userRepositoryTest.authorizeUser(UserRepositoryTest.USER_LOGIN, UserRepositoryTest.USER_PASSWORD)
            userRepositoryTest.saveUserInfoToCache(userInfoModel)
        }
    }

    @Test
    operator fun invoke() = runBlocking {
        val user = loadUserInfoUseCase()
        val userRep = userRepositoryTest.getUserInfoFromCache()
        assert(user == userRep)
    }
}
