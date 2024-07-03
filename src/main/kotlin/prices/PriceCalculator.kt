package prices

abstract class PriceCalculator(val originPrice: Double) {
    abstract var commission: Double

    abstract fun calculateTotalPrice(): Double

    protected val calculateCommissionPrice = { commission.times(originPrice).div(100) }
}
