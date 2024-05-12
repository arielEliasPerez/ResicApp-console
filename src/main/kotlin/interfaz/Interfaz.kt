package interfaz

import data.Product
import data.ProductType
import data.Purchase
import data.User
import prices.PriceCalculator

object Interfaz {
    fun showHomeMain() {
        println("""
            
            ╔═══════════════════════════════════════════════════╗
            ║                 ¡¡¡ RESIC APP !!!                 ║
            ╚═══════════════════════════════════════════════════╝
            
        """.trimIndent())

        print("""
            Elija una opción:
                1 --> Ver catálogo de Libros
                2 --> Ver catálogo de Música
                3 --> Ver historial de compras
                0 --> Salir
            
            --> 
        """.trimIndent())
    }

    fun validateOption(lower: Int, upper: Int): Int {
        var option: Int = readln().toInt()

        while (option !in lower..upper){
            print("¡Opción inválida! Intente otra vez --> ")
            option = readln().toInt()
        }

        return option
    }

    fun showListProduct(objects: List<Any>, productType: ProductType) {
        when(productType){
            ProductType.BOOK -> println("\n\n--------------------------------- LIBROS ---------------------------------\n")
            ProductType.DISC -> println("\n\n--------------------------------- MÚSICA ---------------------------------\n")
        }

        for((i, element) in objects.withIndex())
            println("${i+1} --> $element")

        println("0 --> Volver")
        print("\nSeleccione el nro de libro a comprar--> ")
    }

    fun showPurchaseProcess(user: User, product: Product, productPrice: PriceCalculator) {
        println("\n\n---------------------------------- PROCESO DE COMPRA ----------------------------------\n")
        println("""
            Saldo de ${user.name}:  ${user.money}
            
            Producto a comprar:
                ${product.name}
                    - Autor:                    ${product.author}
                    - Clasificación:            ${product.clasification}
                    - Precio:                   $${productPrice.originPrice}
                    - Comisión adicional:       $${productPrice.calculateCommissionPrice()}
                   
                    - Monto total:              $${productPrice.calculateTotalPrice()}
                   
        """.trimIndent())
    }

    fun confirmPurchase():Boolean{
        println("Confirmar comprar?")
        print("""
            s --> Si
            n --> No
            
            --> 
        """.trimIndent())

        var answer = readln().first()

        while(!answer.equals('s', true) && !answer.equals('n', true)){
            print("¡Respuesta erronea! Intente otra vez -->")
            answer = readln()[0]
        }

        return answer.equals('s', true)
    }

    fun showSuccessfulPurchase() {

        println("""
            
            ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ 
            ░░░░░░░░░░░░░          ¡¡¡COMPRA EXISTOSA!!!        ░░░░░░░░░░░░
            ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ 
            
            Presione enter para continuar...
        """.trimIndent())

        readlnOrNull()
    }

    fun showWrongPurchase() {
        println("¡SALDO INSUFICIENTE!")
        println("No se pudo realizar la compra.")
        println("\nPresione enter para continuar...")
        readlnOrNull()
    }

    fun sayGoodBye(name: String) {
        println("\nHasta luego $name!!!")
        println("\n***************************************************")
    }

    fun showPurchaseHistoryList(purchases: List<Purchase>, products: List<Product>) {
        println("\n\n------------------------------->> HISTORIAL DE COMPRAS <<-------------------------------\n")

        for(purchase in purchases){
            val product = products.find { it.id == purchase.productId }
            if (product != null) {
                println("""
                        ${product.name}
                            - Fecha de compra:              ${purchase.createdDate}
                            - Tipo de Producto:             ${product.type}
                            - Categoría:                    ${product.category}
                            - Autor del Producto:           ${product.author}
                            - Logo del Producto:            ${product.logo}
                            - Clasificación:                ${product.clasification}
                            - Precio del producto:          $${product.price}
                            - Monto Comisión adicional:     $${purchase.amount-product.price}
                            
                            - Monto total pagado:           $${purchase.amount}
                            
                        --------------------------------------------------------------
                    """.trimIndent())
            }
        }

        println("\nPresione enter para volver al Menú Principal...")
        readlnOrNull()
    }

    fun showWelcome() {
        println("""
            ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
                          ¡¡¡ BIENVENIDO !!!
            ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
            
        """.trimIndent())
    }

    fun requestUserName(): String {
        print("► Por favor ingrese su NickName: ")
        return readln()
    }

    fun requestPassword(): String {
        print("\n► Por favor ingrese la contraseña: ")
        return readln()
    }

    fun incorrectUser() {
        println("\n\nEl NickName o la contraseña ingresada es INCORRECTA!!\n")
    }

    fun confirmRetry(): Boolean {
        println("Desea intentarlo otra vez?")
        print("""
            s --> Si
            n --> No
            
            -->
        """.trimIndent())
        var answer = readln().first()

        while(!answer.equals('s', true) && !answer.equals('n', true)){
            print("¡Respuesta erronea! Intente otra vez --> ")
            answer = readln()[0]
        }
        println("")
        return answer.equals('s', true)
    }
}