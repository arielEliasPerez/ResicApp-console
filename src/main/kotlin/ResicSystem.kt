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
            Options.LIBRO -> viewAndBuyBook()
            Options.MUSICA -> viewAndBuyDisc()
            Options.HISTORIAL -> viewPurchaseHistory()
            Options.SALIR -> Interfaz.sayGoodBye(this.user!!.name)
        }
    }

    private fun viewAndBuyBook(){
        val option = viewCatalog(ProductType.BOOK, this.books!!)
        if(option != 0) buy(this.books!![option-1])
    }

    private fun viewAndBuyDisc(){
        val option = viewCatalog(ProductType.DISC, this.discs!!)
        if(option != 0) buy(this.discs!![option-1])
    }

    private fun viewCatalog(productType: ProductType, products: List<Product>): Int{
        Interfaz.showListProduct(products, productType)
        val option = Interfaz.validateOption(lower = 0, upper = products.size)

        return option
    }

    private fun buy(product: Product){
        Interfaz.showPurchaseProcess(this.user!!, product)

        if(!Interfaz.confirmPurchase()) return   //No confirma compra

        val successfulPurchase = PurchaseRepository.processPurchase(product, this.user)

        if(successfulPurchase){
            Interfaz.showSuccessfulPurchase()
            viewPurchaseHistory()
        }else
            Interfaz.showWrongPurchase()
    }

    private fun viewPurchaseHistory(){
        val historyBuys: List<Purchase> = PurchaseRepository.get().filter { it.userId == this.user?.id }
        // añadir proceso para filtrar productos comprados

        Interfaz.showPurchaseHistoryList(historyBuys)
    }
}