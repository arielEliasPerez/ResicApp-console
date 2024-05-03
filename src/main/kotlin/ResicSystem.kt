import data.*
import interfaz.Interfaz
import repositories.ProductRepository
import repositories.PurchaseRepository
import repositories.UserRepository

object ResicSystem {
    private var user: User? = null
    private var books: List<Product>? = null
    private var discs: List<Product>? = null

    fun initLogin(){
        this.user = UserRepository.login()!!
    }

    fun filterProducts(){
        this.books = ProductRepository.get().filter { it.type == ProductType.BOOK }
        this.discs = ProductRepository.get().filter { it.type == ProductType.DISC }
    }

    fun homeMain(): Options{
        Interfaz.showHomeMain()
        val option = Options.values()[Interfaz.validateOption(lower = 0, upper = 3)]

        return option
    }

    fun processOption(option: Options){
        when(option){
            Options.LIBRO -> buyBook()
            Options.MUSICA -> buyDisc()
            Options.HISTORIAL -> seeHistory()
            Options.SALIR -> println("\nAdios ${this.user?.name}!!!")
        }
    }
    private fun buyBook(){
        buy(ProductType.BOOK, this.books!!)
    }

    private fun buyDisc(){
        buy(ProductType.DISC, this.discs!!)
    }

    private fun buy(productType: ProductType, products: List<Product>){
        Interfaz.showListProduct(products, productType)
        val option = Interfaz.validateOption(lower = 0, upper = products.size)

        if(option == 0) return   //Volver atrás
        PurchaseRepository.processPurchase(products[option - 1], this.user)
    }

    private fun seeHistory(){
        //val historyBuys: List<Purchase> = PurchaseRepository.get().filter { it.userId == this.user?.id }

    }
}