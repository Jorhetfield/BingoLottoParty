package es.jrhtfld.mvvm_template.setup.client

import es.jrhtfld.data.*
import es.jrhtfld.domain.client.MockClient

class MockApiClient : MockClient {

    override suspend fun provideProductsList(): Either<String, List<Products>> =
        value(mockProductList)

    override suspend fun provideProductsByIdentifier(identifier: Int): Either<String, Products> {
        val product = mockProductList.find { it.id == identifier }
        return if (product != null) value(product) else customError("No se ha encontrado el producto")
    }

    private val mockProductList = listOf(
        Products(0, "Producto 1", 10.00f, "", "Lorem ipsum", ProductType.FIRST),
        Products(1, "Producto 2", 10.00f, "", "Lorem ipsum", ProductType.FIRST),
        Products(2, "Producto 3", 10.00f, "", "Lorem ipsum", ProductType.FIRST),
        Products(3, "Producto 4", 10.00f, "", "Lorem ipsum", ProductType.FIRST),
        Products(4, "Producto 5", 10.00f, "", "Lorem ipsum", ProductType.FIRST),
        Products(5, "Producto 6", 10.00f, "", "Lorem ipsum", ProductType.SECOND),
        Products(6, "Producto 7", 10.00f, "", "Lorem ipsum", ProductType.SECOND),
        Products(7, "Producto 8", 10.00f, "", "Lorem ipsum", ProductType.SECOND),
        Products(8, "Producto 9", 10.00f, "", "Lorem ipsum", ProductType.SECOND),
        Products(9, "Producto 10", 10.00f, "", "Lorem ipsum", ProductType.SECOND)
    )
}