package interfaz

import data.Product
import data.ProductType
import data.User

object Interfaz {
    fun showHomeMain() {
        println("\n\n***************************************************")
        println("******************** RESIC APP ********************")
        println("***************************************************\n")
        print("""
            Elija una opción:
                1 --> Ver catálogo de Libros
                2 --> Ver catálogo de Albumes
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

    fun showPurchaseHistoryList(objects: List<Any>) {
        println("\n\n------------------------------- HISTORIAL DE COMPRAS -------------------------------\n")
        //seguir editando este método
        objects.map { println(it) }
        println("\nPresione enter para volver al Menú Principal...")
        readlnOrNull()
    }

    fun showPurchaseProcess(user: User, product: Product) {
        println("\n\n---------------------------------- PROCESO DE COMPRA ----------------------------------\n")
        println("""
            Saldo de ${user.name}:  ${user.money}
            
            Producto a comprar:
                ${product.name}
                    -Autor:               ${product.author}
                    -Precio:              ${product.price}
                    -Comisión adicional:  producto.comision
                   
                   -Monto total:          producto.calcularPrecioTotal
                   
        """.trimIndent())
    }

    fun confirmPurchase():Boolean{
        println("Confirmar comprar?")
        print("""
            y --> Yes
            n --> No
            
            --> 
        """.trimIndent())

        var answer = readln().first()

        while(!answer.equals('y', true) && !answer.equals('n', true)){
            print("¡Respuesta erronea! Intente otra vez -->")
            answer = readln()[0]
        }

        return answer.equals('y', true)
    }

    fun showSuccessfulPurchase() {
        println("¡¡¡COMPRA EXISTOSA!!!")
        println("Presione enter para continuar")
        readlnOrNull()
    }

    fun showWrongPurchase() {
        println("¡SALDO INSUFICIENTE!")
        println("No se pudo realizar la compra.")
        println("Presione enter para continuar")
        readlnOrNull()
    }

    fun sayGoodBye(name: String) {
        println("\nHasta luego $name!!!")
        println("\n***************************************************")
    }
}