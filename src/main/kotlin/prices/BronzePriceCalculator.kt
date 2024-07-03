package prices

class BronzePriceCalculator(originPrice: Double) : PriceCalculator(originPrice) {
    override var commission = 0.0

    override fun calculateTotalPrice(): Double {
        return this.originPrice + this.commission
    }
}