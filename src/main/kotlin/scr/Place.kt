import java.time.LocalTime

interface Place {
    fun cost(time: LocalTime) : Double

    fun getCostOf(time: LocalTime, minuteCost: Double) : Double {
        val hoursCost = time.hour * ( minuteCost * 60 ) // cost the hour
        val minutesCost = time.minute * minuteCost
        val secondsCost = time.second * ( minuteCost / 60 ) // cost the second
        return hoursCost + minutesCost + secondsCost
    }
}