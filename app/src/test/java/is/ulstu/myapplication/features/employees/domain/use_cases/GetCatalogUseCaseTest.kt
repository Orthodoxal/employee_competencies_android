package `is`.ulstu.myapplication.features.employees.domain.use_cases

import `is`.ulstu.myapplication.data.repository.EmployeesRepositoryTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCatalogUseCaseTest {

    private lateinit var employeesRepositoryTest: EmployeesRepositoryTest
    private lateinit var getCatalogUseCase: GetCatalogUseCase

    @Before
    fun setUp() {
        employeesRepositoryTest = EmployeesRepositoryTest()
        getCatalogUseCase = GetCatalogUseCase(employeesRepositoryTest)
    }

    @Test
    operator fun invoke() = runBlocking {
        val cache = getCatalogUseCase()
        assert(cache.isEmpty())
        val api = employeesRepositoryTest.loadEmployeesCatalog()
        employeesRepositoryTest.setEmployeesCatalogToCache(api)
        val cache2 = getCatalogUseCase()
        assert(cache2.isNotEmpty())
    }

    @Test
    fun invokeLoaded() = runBlocking {
        val api = employeesRepositoryTest.loadEmployeesCatalog()
        employeesRepositoryTest.setEmployeesCatalogToCache(api)
        val cache2 = getCatalogUseCase()
        assert(cache2.isNotEmpty())
    }
}
