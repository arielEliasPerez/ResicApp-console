package prices

import java.time.DayOfWeek
import java.time.LocalDate

class PlatinumPriceCalculator(productID: Long, originPrice: Double) : PriceCalculator(productID, originPrice) {

    companion object{
        val SATURDAY = DayOfWeek.SATURDAY
        val SUNDAY = DayOfWeek.SUNDAY
    }

    override var commission = 0.75

    override fun calculateTotalPrice(): Double {
        if (isValidDate()) this.commission = 3.0

        val commissionPrice = super.calculateCommissionPrice()

        return this.originPrice + commissionPrice
    }

    private fun isValidDate(): Boolean {
        val currentDay = LocalDate.now().dayOfWeek

        return currentDay == SATURDAY || currentDay == SUNDAY
    }
}