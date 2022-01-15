package es.jrhtfld.ineed.setup.network

import okhttp3.Headers

sealed class ResponseResult<out T : Any> {
    data class Success<out T : Any>(val value: T, val headers: Headers = Headers.headersOf()) : ResponseResult<T>()
    data class NotContent(val message: String) : ResponseResult<Nothing>()
    data class Empty(val message: String) : ResponseResult<Nothing>()
    data class Error(val message: String, val cause: Exception? = null) : ResponseResult<Nothing>()
    data class Forbidden(val message: String) : ResponseResult<Nothing>()
    data class Unauthorized(val message: String) : ResponseResult<Nothing>()
    data class UnexistingMail(val message: String) : ResponseResult<Nothing>()
    data class AlreadyRegistered(val message: String) : ResponseResult<Nothing>()
}