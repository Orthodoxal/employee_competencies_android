package `is`.ulstu.myapplication.base.api

open class AppException(message: String, cause: Throwable) : Exception(message, cause)

class ConnectionException(cause: Throwable) : AppException(message = "Ошибка соединения с сервером", cause = cause)

class ParseBackendResponseException(cause: Throwable) : AppException(message = "Неизвестный ответ сервера", cause = cause)

data class BackendException(val error: ErrorResponseBody) : Exception()
