package `is`.ulstu.myapplication.features.employees.domain.use_cases

import `is`.ulstu.myapplication.data.repository.EmployeesRepositoryTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FindEmployeeByNameUseCaseTest {
    private lateinit var employeesRepositoryTest: EmployeesRepositoryTest
    private lateinit var findEmployeeByNameUseCase: FindEmployeeByNameUseCase

    @Before
    fun setUp() {
        employeesRepositoryTest = EmployeesRepositoryTest()
        findEmployeeByNameUseCase = FindEmployeeByNameUseCase(employeesRepositoryTest)
        runBlocking {
            val api = employeesRepositoryTest.loadEmployeesCatalog()
            employeesRepositoryTest.setEmployeesCatalogToCache(api)
        }
    }

    @Test
    operator fun invoke() = runBlocking {
        val cache = employeesRepositoryTest.getEmployeesCatalogFromCache()
        val fullName = cache.random().fullName
        val employee = findEmployeeByNameUseCase(fullName)
        assert(employee.all { it.fullName.contains(fullName) })
    }
}
