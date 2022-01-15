package es.jrhtfld.domain.repository

import es.jrhtfld.data.Either
import es.jrhtfld.data.PathResponse
import es.jrhtfld.domain.client.CustomClient
import okhttp3.MultipartBody

class ExampleRepository(private val customClient: CustomClient) {

    suspend fun exampleRepositoryService(file: MultipartBody.Part): Either<String, PathResponse> = customClient.exampleClientService(file)
}