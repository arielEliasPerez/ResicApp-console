package interfaz

import data.ProductType

object Interfaz {
    fun showHomeMain() {
        //Mostrar menu principal por consola
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
            print("¡Opción inválida! Intente otra vez:\n-->")
            option = readln().toInt()
        }

        return option
    }

    fun showListProduct(objects: List<Any>, productType: ProductType) {
        when(productType){
            ProductType.BOOK -> println("\n---------------------------- Libros ----------------------------\n")
            ProductType.DISC -> println("\n---------------------------- Música ----------------------------\n")
        }

        for((i, element) in objects.withIndex())
            println("${i+1} --> $element")

        println("0 --> Volver")
        print("\nSeleccione el nro de libro a comprar--> ")
    }

    fun showListHistoyBuys(objects: List<Any>, productType: ProductType) {

    }
}