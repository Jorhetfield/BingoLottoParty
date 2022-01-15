package es.jrhtfld.data

data class CustomResponse<T>(
    val statusCode: Int,
    val response: T
)