import java.time.LocalTime

class Call(val place:Place, val time:LocalTime) {
    fun getCost() : Double{
        return place.cost(time)
    }
}