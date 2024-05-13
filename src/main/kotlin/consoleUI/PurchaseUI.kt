package consoleUI

import data.Product
import data.User
import prices.PriceCalculator

object PurchaseUI {
    fun showPurchaseProcess(user: User, product: Product, productPrice: PriceCalculator) {
        println("\n\n====================================================== PROCESO DE COMPRA ======================================================\n")
        println(
            """
            Saldo de ${user.name}:              $ ${user.money}
            
            Producto a comprar:
                ${product.name}
                    - Autor:                    ${product.author}
                    - Clasificación:            ${product.clasification}
                    - Precio:                   $ ${productPrice.originPrice}
                    - Comisión adicional:       $ ${productPrice.calculateTotalPrice().minus(product.price)}
                   
                    - Monto total:              $ ${productPrice.calculateTotalPrice()}
                   
        """.trimIndent()
        )
    }

    fun confirmPurchase(): Boolean {
        println("Confirmar comprar?")
        print(
            """
            s --> Si
            n --> No
            
            --> 
        """.trimIndent()
        )

        var answer = readln().first()

        while (!answer.equals('s', true) && !answer.equals('n', true)) {
            print("¡Respuesta erronea! Intente otra vez --> ")
            answer = readln()[0]
        }

        return answer.equals('s', true)
    }

    fun showSuccessfulPurchase() {
        println(
            """
            
            ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ 
            ░░░░░░░░░░░░░          ¡¡¡COMPRA EXISTOSA!!!        ░░░░░░░░░░░░
            ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ 
            
            Presione enter para continuar...
        """.trimIndent()
        )

        readlnOrNull()
        clearBuffer()
    }

    private fun clearBuffer() {
        while (System.`in`.available() > 0) {
            System.`in`.read()
        }
    }

    fun showWrongPurchase() {
        println("¡SALDO INSUFICIENTE!")
        println("No se pudo realizar la compra.")
        println("\nPresione enter para continuar...")
        readlnOrNull()
    }
}