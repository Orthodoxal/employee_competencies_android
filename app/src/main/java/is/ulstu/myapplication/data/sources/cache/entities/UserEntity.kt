package `is`.ulstu.myapplication.data.sources.cache.entities

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val fullName: String,
    val department: String,
    val position: String,
    val city: String,
    val token: String,
)
