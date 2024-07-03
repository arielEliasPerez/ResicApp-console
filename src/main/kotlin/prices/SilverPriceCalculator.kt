package prices

import java.time.LocalTime

class SilverPriceCalculator(productID: Long, originPrice: Double) : PriceCalculator(productID, originPrice) {
    
    companion object{
        val MINIMUM_TIME: LocalTime = LocalTime.of(15, 0)
        val MAXIMUM_TIME: LocalTime = LocalTime.of(22, 30)
    }
    
    override var commission = 3.0

    override fun calculateTotalPrice(): Double {
        if (isValidTime()) this.commission = 1.0

        val commissionPrice = super.calculateCommissionPrice()

        return originPrice + commissionPrice
    }


    private fun isValidTime(): Boolean {
        val currentTime = LocalTime.now()
        
        val isTimeValid = currentTime in MINIMUM_TIME..MAXIMUM_TIME

        return isTimeValid
    }
}