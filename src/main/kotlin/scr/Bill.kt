class Bill {
    val calls: MutableList<Call> = mutableListOf()

    fun getTotalCost() : Double{
        return calls.sumOf { it.getCost() }
    }

    fun addCall(call: Call) {
        calls.add(call)
    }
}