import data.Options
import data.Product
import data.ProductType
import data.User
import interfaz.Interfaz
import repositories.ProductRepository
import repositories.PurchaseRepository
import repositories.UserRepository

fun main(args: Array<String>) {
    val user = UserRepository.login()

    do{
        Interfaz.showHomeMain()
        val option = Options.values()[Interfaz.validateOption(lower = 0, upper = 3)]
        classifyOption(option, user)     //Es buena idea estar mandando user por muchos parametros?
    }while(option != Options.SALIR);

}

fun classifyOption(option: Options, user: User?){
    when(option){
        Options.HISTORIAL -> seeHistory(option, user)
        Options.SALIR -> println("\nAdios!!!")
        else -> buy(option, user)         //ver libros o ver musicas
    }
}

fun buy(option: Options, user: User?){
    //Es buena idea filtrar prods cada vez que elija las opciones Libros o Musica?
    val productType = if(option == Options.LIBRO) ProductType.BOOK else ProductType.DISC
    val products: List<Product> = ProductRepository.get().filter { it.type == productType }

    Interfaz.showListProduct(products, option)
    val option: Int = Interfaz.validateOption(lower = 0, upper = products.size)

    if(option == 0) return   //Volver atrás
    PurchaseRepository.processPurchase(products[option - 1], user)
}

fun seeHistory(option: Options, user: User?){
    //mostrar historial de compras del usuario
}

/*
    Para optimizar lo señalado en los comentarios previos,
    se podría considerar usar una clase ResicSystem (o el nombre a discutir)
    donde liberará el trabajo del Main,
    tendrá propiedades donde se alojen las listas filtradas al comienzo del programa,
    tendrá una propiedad user para que se guarde el usuario logueado y poder usarlo en cualquier metodo
 */