package es.jrhtfld.domain.error

import es.jrhtfld.data.Either
import es.jrhtfld.data.value
import es.jrhtfld.data.customError
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkController {

    fun checkException(e: Exception): Either<String, Nothing> {
        return when (e) {
            is UnknownHostException -> customError(MessageError.networkDown)
            is SocketTimeoutException -> customError(MessageError.timeOut)
            is NullPointerException -> customError(MessageError.notFound)
            else -> customError(MessageError.errorGeneral)
        }
    }

    fun <T : Any> checkResponse(response: Response<T>): Either<String, T> {

        return when (response.code()) {

            in 200..299 -> if (response.body() != null) {
                value(response.body() as T)
            } else customError(response.errorBody().toString())

            else -> customError(checkError(response.code()))
        }
    }

    private fun checkError(errorCode: Int): String {
        return when (errorCode) {
            303 -> MessageError.wrongLogin
            401 -> MessageError.unauthorized
            403 -> MessageError.forbidden
            404 -> MessageError.notFound
            405 -> MessageError.methodNotAllowed
            in 500..503 -> MessageError.networkDown
            800 -> MessageError.updateApp
            else -> MessageError.errorGeneral
        }
    }
}