package `is`.ulstu.myapplication.features.authorization.domain.models

data class UserInfoModel(
    val fullName: String,
    val department: String,
    val position: String,
    val city: String,
    val token: String,
)