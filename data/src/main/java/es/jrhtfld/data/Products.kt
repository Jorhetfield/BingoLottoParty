package es.jrhtfld.data

data class Products(
    val id: Int,
    val name: String,
    val price: Float,
    val image: String,
    val description: String,
    val type: ProductType
)

enum class ProductType {
    FIRST, SECOND
}