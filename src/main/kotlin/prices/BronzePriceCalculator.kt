package prices

class BronzePriceCalculator(productID: Long, originPrice: Double) : PriceCalculator(productID, originPrice) {
    override var commission = 0.0

    override fun calculateTotalPrice(): Double {
        return this.originPrice + this.commission
    }
}