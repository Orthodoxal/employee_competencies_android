package `is`.ulstu.myapplication.features.authorization.domain.use_cases

import `is`.ulstu.myapplication.data.repository.UserRepositoryTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SignInUseCaseTest {
    private lateinit var userRepositoryTest: UserRepositoryTest
    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setUp() {
        userRepositoryTest = UserRepositoryTest()
        signInUseCase = SignInUseCase(userRepositoryTest)
    }

    @Test
    fun signIn() = runBlocking {
        signInUseCase.signIn(UserRepositoryTest.USER_LOGIN, UserRepositoryTest.USER_PASSWORD)
        val user = userRepositoryTest.getUserInfoFromCache()
        assert(user != null)
    }

    @Test
    fun signInByToken() = runBlocking {
        signInUseCase.signIn(UserRepositoryTest.USER_LOGIN, UserRepositoryTest.USER_PASSWORD)
        signInUseCase.signInByToken()
        val user = userRepositoryTest.getUserInfoFromCache()
        assert(user != null)
    }
}
