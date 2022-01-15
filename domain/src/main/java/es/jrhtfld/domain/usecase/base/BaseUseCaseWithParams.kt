package es.jrhtfld.domain.usecase.base

interface BaseUseCaseWithParams<out T, in P> {

    suspend fun execute(params: P): T
}