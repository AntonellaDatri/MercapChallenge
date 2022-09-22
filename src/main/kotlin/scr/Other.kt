import java.time.LocalTime

open class Other : Place {
    open var costXMinutes: Double = 0.0

    override fun cost(time: LocalTime): Double {
        return getCostOf(time, costXMinutes)
    }
}