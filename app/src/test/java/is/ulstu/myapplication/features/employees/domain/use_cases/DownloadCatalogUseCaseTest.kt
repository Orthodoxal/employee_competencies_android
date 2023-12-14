package `is`.ulstu.myapplication.features.employees.domain.use_cases

import `is`.ulstu.myapplication.data.repository.EmployeesRepositoryTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DownloadCatalogUseCaseTest {
    private lateinit var employeesRepositoryTest: EmployeesRepositoryTest
    private lateinit var downloadCatalogUseCase: DownloadCatalogUseCase

    @Before
    fun setUp() {
        employeesRepositoryTest = EmployeesRepositoryTest()
        downloadCatalogUseCase = DownloadCatalogUseCase(employeesRepositoryTest)
    }

    @Test
    operator fun invoke() = runBlocking {
        val cache = downloadCatalogUseCase()
        val api = employeesRepositoryTest.loadEmployeesCatalog()
        assertEquals(api, cache)
    }
}
