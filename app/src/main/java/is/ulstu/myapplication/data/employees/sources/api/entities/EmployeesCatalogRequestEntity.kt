package `is`.ulstu.myapplication.data.employees.sources.api.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeesCatalogRequestEntity(
    val token: String
)
