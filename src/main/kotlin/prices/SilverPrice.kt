package prices

import java.time.LocalTime

class SilverPrice(productID: Long, originPrice: Double) : PriceCalculator(productID, originPrice) {
    override var commission = 3.0

    override fun calculateTotalPrice(): Double {
        if(isValidTime()) this.commission = 1.0

        val commissionPrice = super.calculateCommissionPrice()

        return originPrice + commissionPrice
    }

    private fun isValidTime(): Boolean {
        val minimumHours = 15
        val maximumHours = 22
        val minimumMinutes = 0
        val maximumMinutes = 30
        val currentHours = LocalTime.now().hour
        val currentMinutes = LocalTime.now().minute

        val isTimeValid = currentHours in minimumHours..maximumHours &&
                currentMinutes in minimumMinutes..maximumMinutes

        return isTimeValid
    }
}