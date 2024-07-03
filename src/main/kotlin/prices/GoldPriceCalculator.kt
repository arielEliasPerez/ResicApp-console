package prices

class GoldPriceCalculator(productId: Long, originPrice: Double) : PriceCalculator(productId, originPrice) {
    override var commission: Double = 2.0

    override fun calculateTotalPrice(): Double {
        val commissionPrice = super.calculateCommissionPrice()
        return originPrice.plus(commissionPrice)
    }
}