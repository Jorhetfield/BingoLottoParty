package es.jrhtfld.domain.client

import es.jrhtfld.data.Either
import es.jrhtfld.data.PathResponse
import okhttp3.MultipartBody

interface CustomClient {

    suspend fun exampleClientService(file: MultipartBody.Part): Either<String, PathResponse>

}