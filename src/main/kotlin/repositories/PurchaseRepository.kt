package repositories

import data.Purchase
import data.User
import prices.PriceCalculator
import java.time.LocalDate

object PurchaseRepository {

    private val purchases = mutableListOf<Purchase>()

    init {
        purchases.add(Purchase(1L, 1504L, 1L, 350.50, "2023/01/01"))
        purchases.add(Purchase(2L, 1504L, 4L, 100.75, "2023/01/01"))
        purchases.add(Purchase(3L, 1504L, 7L, 250.50, "2023/01/01"))
        purchases.add(Purchase(4L, 1504L, 10L, 50.00, "2023/01/01"))
        purchases.add(Purchase(5L, 1504L, 3L, 1350.15, "2023/01/01"))
        purchases.add(Purchase(6L, 2802L, 2L, 20.30, "2023/01/01"))
        purchases.add(Purchase(7L, 2802L, 9L, 450.75, "2023/01/01"))
        purchases.add(Purchase(8L, 2802L, 2L, 500.00, "2023/01/01"))
        purchases.add(Purchase(9L, 1510L, 6L, 350.50, "2023/01/01"))
        purchases.add(Purchase(10L, 1510L, 5L, 150.00, "2023/01/01"))
    }

    fun processPurchase(priceProduct: PriceCalculator, user: User): Boolean {
        val totalPrice = priceProduct.calculateTotalPrice()

        if (totalPrice > user.money) return false

        val idPurchase = (this.get().size + 1).toLong()
        val userId: Long = user.id
        val productId: Long = priceProduct.productId
        val createdData = "${LocalDate.now().year}/${LocalDate.now().month.value}/${LocalDate.now().dayOfMonth}"

        val purchase = Purchase(idPurchase, userId, productId, totalPrice, createdData)

        user.money -= totalPrice
        this.add(purchase)

        return true
    }

    private fun add(purchase: Purchase) {
        this.purchases.add(0, purchase)
    }

    fun get(): List<Purchase> {
        return this.purchases
    }

}