package consoleUI

import data.Product
import data.ProductType
import data.Purchase

object HomeMainUI {
    fun showHomeMain() {
        println(
            """
            
            ╔═══════════════════════════════════════════════════╗
            ║                 ¡¡¡ RESIC APP !!!                 ║
            ╚═══════════════════════════════════════════════════╝
            
        """.trimIndent()
        )

        print(
            """
            Elija una opción:
                1 --> Ver catálogo de Libros
                2 --> Ver catálogo de Música
                3 --> Ver historial de compras
                0 --> Salir
            
            --> 
        """.trimIndent()
        )
    }

    fun validateOption(lower: Int, upper: Int): Int {
        var option: Int = enterAnInteger()

        while (option !in lower..upper) {
            print("¡Opción inválida! Intente otra vez --> ")
            option = enterAnInteger()
        }

        return option
    }

    private fun enterAnInteger(): Int {
        while (true) {
            try {
                return readln().toInt()
            } catch (e: NumberFormatException) {
                print("¡Opción inválida! Intente otra vez --> ")
            }
        }
    }

    fun showListProduct(objects: List<Any>, productType: ProductType) {
        when (productType) {
            ProductType.BOOK -> println("\n\n====================================================== LIBROS ======================================================\n")
            ProductType.DISC -> println("\n\n====================================================== MÚSICA ======================================================\n")
        }

        for ((i, element) in objects.withIndex())
            println("${i + 1} --> $element")

        println("0 --> Volver")
        print("\nSeleccione el nro de producto a comprar--> ")
    }

    fun showPurchaseHistoryList(purchases: List<Purchase>, products: List<Product>) {
        println("\n\n====================================================== HISTORIAL DE COMPRAS ======================================================\n")

        for (purchase in purchases) {
            val product = products.find { it.id == purchase.productId }
            if (product != null) {
                println(
                    """
                        ${product.name}
                            - Fecha de compra:              ${purchase.createdDate}
                            - Tipo de Producto:             ${product.type}
                            - Categoría:                    ${product.category}
                            - Autor del Producto:           ${product.author}
                            - Logo del Producto:            ${product.logo}
                            - Clasificación:                ${product.clasification}
                            - Precio del producto:          $ ${product.price}
                            - Monto Comisión adicional:     $ ${purchase.amount.minus(product.price)}
                            
                            - Monto total pagado:           $ ${purchase.amount}
                            
                        --------------------------------------------------------------
                    """.trimIndent()
                )
            }
        }

        println("\nPresione enter para volver al Menú Principal...")
        readlnOrNull()
        clearBuffer()
    }

    private fun clearBuffer() {
        while (System.`in`.available() > 0) {
            System.`in`.read()
        }
    }

    fun sayGoodBye(name: String) {
        println("\nHasta luego $name!!!")
        println("\n***************************************************")
    }
}