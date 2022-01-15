package es.jrhtfld.domain.client

import es.jrhtfld.data.Either
import es.jrhtfld.data.Products

interface MockClient {

    suspend fun provideProductsList(): Either<String, List<Products>>

    suspend fun provideProductsByIdentifier(identifier: Int): Either<String, Products>
}