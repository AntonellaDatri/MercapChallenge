package scr.places

import java.time.LocalTime
import kotlin.math.roundToInt

interface Place {
    val placeName:String
    fun cost(time: LocalTime) : Double

    fun getCostOf(time: LocalTime, minuteCost: Double) : Double {
        val hoursCost = time.hour * ( minuteCost * 60 ) // cost the hour
        val minutesCost = time.minute * minuteCost
        val secondsCost = time.second * ( minuteCost / 60 ) // cost the second
        val cost = ((hoursCost + minutesCost + secondsCost )* 100.0).roundToInt() / 100.0
        if (cost > 0.0){
            println(placeName + " | " + time + " | " + minuteCost + " | " + cost)
        }
        return cost
    }
}