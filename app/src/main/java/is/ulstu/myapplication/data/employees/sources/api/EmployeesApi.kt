package `is`.ulstu.myapplication.data.employees.sources.api

import `is`.ulstu.myapplication.data.employees.sources.api.entities.EmployeeResponseEntity
import retrofit2.http.GET

interface EmployeesApi {

    @GET("catalog")
    suspend fun getEmployeesCatalog(): List<EmployeeResponseEntity>
}
