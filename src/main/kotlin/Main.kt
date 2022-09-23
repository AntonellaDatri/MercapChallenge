import scr.places.International
import scr.places.Local
import scr.places.National
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

fun main() {
    val bill = Bill()
    init(bill)
    bill.getTotalCost()
}

fun init(bill : Bill){
    val cordobaCity = National(2.1, "Cordoba, Cordoba")
    val posadas = National(1.1, "Posadas, Misiones")
    val USA = International(5.5, "United States")
    val Bolivia = International(3.5, "Bolivia")
    val timeBusinessDaysAt9Am = LocalDateTime.of(2022, Month.SEPTEMBER, 22, 9, 0,0)
    val timeBusinessDaysAt21Pm = LocalDateTime.of(2022, Month.SEPTEMBER, 22, 21, 0,0)


    val list = mutableListOf<Call>(
            Call(Local(timeBusinessDaysAt9Am), LocalTime.of(1,35,42)),
            Call(Local(timeBusinessDaysAt21Pm), LocalTime.of(0,45,5)),
            Call(Local(timeBusinessDaysAt21Pm), LocalTime.of(12,5,5)),
            Call(cordobaCity, LocalTime.of(2,0,23)),
            Call(posadas, LocalTime.of(1,14,40)),
            Call(USA, LocalTime.of(0,15,33)),
            Call(Bolivia, LocalTime.of(1,0,15))
    )

    list.forEach { bill.addCall(it) }
}