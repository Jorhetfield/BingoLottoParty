package es.jrhtfld.domain.usecase

import es.jrhtfld.data.Either
import es.jrhtfld.data.PathResponse
import es.jrhtfld.domain.provider.ExampleProvider
import es.jrhtfld.domain.usecase.base.BaseUseCaseWithParams
import okhttp3.MultipartBody

class ExampleUseCase(
    private val exampleProvider: ExampleProvider
) : BaseUseCaseWithParams<Either<String, PathResponse>, MultipartBody.Part> {

    override suspend fun execute(params: MultipartBody.Part): Either<String, PathResponse> = exampleProvider.exampleProviderService(params)
}