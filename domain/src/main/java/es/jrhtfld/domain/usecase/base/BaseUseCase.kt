package es.jrhtfld.domain.usecase.base

interface BaseUseCase<T> {

    suspend fun execute(): T
}