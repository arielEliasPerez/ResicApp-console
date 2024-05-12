import data.*
import consoleUI.*
import prices.*
import repositories.ProductRepository
import repositories.PurchaseRepository
import repositories.UserRepository

object ResicSystem {
    private var user: User? = null
    private var books: List<Product>? = null
    private var discs: List<Product>? = null

    fun initLogin(){
        LoginUI.showWelcome()
        var userName = LoginUI.requestUserName()
        var password = LoginUI.requestPassword()
        this.user = UserRepository.login(userName, password)

        while(user == null){
            LoginUI.incorrectUser()
            val retry = LoginUI.confirmRetry()

            if(!retry) throw Exception("\n¡Fin del Programa!\n")

            userName = LoginUI.requestUserName()
            password = LoginUI.requestPassword()
            this.user = UserRepository.login(userName, password)
        }

    }

    fun filterProducts(){
        this.books = ProductRepository.get().filter { it.type == ProductType.BOOK }
        this.discs = ProductRepository.get().filter { it.type == ProductType.DISC }
    }

    fun homeMain(): Options{
        HomeMainUI.showHomeMain()
        val option = Options.values()[HomeMainUI.validateOption(lower = 0, upper = Options.values().size - 1)]

        return option
    }

    fun processOption(option: Options){
        when(option){
            Options.LIBRO -> viewAndBuyBook()
            Options.MUSICA -> viewAndBuyDisc()
            Options.HISTORIAL -> viewPurchaseHistory()
            Options.SALIR -> HomeMainUI.sayGoodBye(this.user!!.name)
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
        HomeMainUI.showListProduct(products, productType)
        val option = HomeMainUI.validateOption(lower = 0, upper = products.size)

        return option
    }

    private fun buy(product: Product){
        val productPrice: PriceCalculator = when(product.clasification){
            ProductClasification.GOLD -> GoldPrice(product.id, product.price)
            ProductClasification.SILVER -> SilverPrice(product.id, product.price)
            ProductClasification.PLATINUM -> PlatinumPrice(product.id, product.price)
            else -> BronzePrice(product.id, product.price)
        }

        PurchaseUI.showPurchaseProcess(this.user!!, product, productPrice)

        if(!PurchaseUI.confirmPurchase()) return   //No confirma compra

        val successfulPurchase = PurchaseRepository.processPurchase(productPrice, this.user)

        if(successfulPurchase){
            PurchaseUI.showSuccessfulPurchase()
            viewPurchaseHistory()
        }else
            PurchaseUI.showWrongPurchase()
    }


    private fun viewPurchaseHistory(){
        val historyBuys: List<Purchase> = PurchaseRepository.get().filter { it.userId == this.user?.id }

        HomeMainUI.showPurchaseHistoryList(historyBuys, ProductRepository.get())
    }
}