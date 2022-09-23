import kotlin.math.roundToInt

class Bill {
    val calls: MutableList<Call> = mutableListOf()

    fun getTotalCost() : Double{
        println(" Call destination | Call time | Cost per minute | total")
        val total = (calls.sumOf { it.getCost() }*100).roundToInt() / 100.0
        println(" Total            |           |                 | " + total )
        return total
    }

    fun addCall(call: Call) {
        calls.add(call)
    }
}