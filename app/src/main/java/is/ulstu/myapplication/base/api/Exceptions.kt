package `is`.ulstu.myapplication.base.api

open class AppException(message: String, cause: Throwable? = null) : Exception(message, cause)

class ConnectionException(cause: Throwable) : AppException(message = "Ошибка соединения с сервером", cause = cause)

class ParseBackendResponseException(cause: Throwable) : AppException(message = "Неизвестный ответ сервера", cause = cause)

data class BackendException(val error: ErrorResponseBody) : AppException(message = error.message)

class BusinessLogicException(message: String) : AppException(message = message)
