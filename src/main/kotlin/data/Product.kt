package data

data class Product(
    val id: Long,
    val name: String,
    val type: ProductType,
    val clasification: ProductClasification,
    val releasedDate: String,
    val category: String,
    val stars: Double,
    val price: Double,
    val logo: String,
    val author: String


) {
    override fun toString(): String {
        return """'$name'
            clasificación:       $clasification
            fecha lanzamiento:  '$releasedDate'
            categoría:          '$category'
            stars:               $stars
            precio:              $price
            logo:                '$logo'
            autor:               '$author'
            
        """.trimMargin()
    }
}