package prices

abstract class PriceCalculator (val productId: Long, val originPrice: Double){
    abstract var commission: Double

    abstract fun calculateTotalPrice(): Double

    open val calculateCommissionPrice = {commission * originPrice/100}
}
