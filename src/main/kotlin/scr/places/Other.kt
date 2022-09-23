package scr.places

import java.time.LocalTime

open class Other(override val placeName: String, open var costXMinutes: Double) : Place {
    override fun cost(time: LocalTime): Double {
        return getCostOf(time, costXMinutes)
    }
}