package `is`.ulstu.myapplication.data.employees.sources.api

import `is`.ulstu.myapplication.base.api.BaseRetrofitSource
import `is`.ulstu.myapplication.base.api.RetrofitConfig
import `is`.ulstu.myapplication.data.employees.sources.api.entities.EmployeeResponseEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitEmployeesSource @Inject constructor(
    config: RetrofitConfig
) : BaseRetrofitSource(config), EmployeesSource {
    private val employeesApi = retrofit.create(EmployeesApi::class.java)

    override suspend fun getEmployeesCatalog(): List<EmployeeResponseEntity> =
        wrapRetrofitExceptions { employeesApi.getEmployeesCatalog() }
}
