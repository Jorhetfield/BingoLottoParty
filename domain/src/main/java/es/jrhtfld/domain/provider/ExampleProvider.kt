package es.jrhtfld.domain.provider

import es.jrhtfld.data.Either
import es.jrhtfld.data.PathResponse
import es.jrhtfld.domain.repository.ExampleRepository
import okhttp3.MultipartBody

class ExampleProvider(private val exampleRepository: ExampleRepository) {

    suspend fun exampleProviderService(file: MultipartBody.Part): Either<String, PathResponse> = exampleRepository.exampleRepositoryService(file)
}