import data.Options
import data.Product
import data.ProductType
import interfaz.Interfaz
import repositories.ProductRepository
import repositories.PurchaseRepository
import repositories.UserRepository

fun main(args: Array<String>) {
    val user = UserRepository.login()

    do{
        Interfaz.showHomeMain()
        val option = Options.values()[Interfaz.validateOption(lower = 0, upper = 3)]
        classifyOption(option)
    }while(option != Options.SALIR);

}

fun classifyOption(option: Options){
    when(option){
        Options.HISTORIAL -> seeHistory(option)
        Options.SALIR -> println("Adios!!!")
        else -> buy(option)
    }
}

fun buy(option: Options){
    val productType = if(option == Options.LIBRO) ProductType.BOOK else ProductType.DISC

    val products: List<Product> = ProductRepository.get().filter { it.type == productType }
    Interfaz.showListProduct(products, option)
    val option: Int = Interfaz.validateOption(lower = 0, upper = products.size)
    PurchaseRepository.processPurchase(products[option])
}

fun seeHistory(option: Options){
}
