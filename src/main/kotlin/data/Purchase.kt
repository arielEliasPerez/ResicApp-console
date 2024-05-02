package data

data class Purchase(
    val id: Long,
    val userId: Long,
    val productId: Long,
    val amount: Double,
    val createdDate: String,

) {
    override fun toString(): String {
        return """
            ID Compra:      $id
            ID usuario:     $userId
            ID Producto:    $productId
            Monto:          $amount
            Fecha de op:    '$createdDate'
            """.trimIndent()
    }
}
