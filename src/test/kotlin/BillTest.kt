import org.junit.jupiter.api.Test
import scr.places.International
import scr.places.Local
import scr.places.National
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

class BillTest {
    val cordobaCity = National(2.1, "Cordoba, Cordoba")
    val posadas = National(1.1, "Posadas, Misiones")
    val USA = International(5.5, "United States")
    val Bolivia = International(3.5, "Bolivia")
    val timeBusinessDaysAt9Am = LocalDateTime.of(2022, Month.SEPTEMBER, 22, 9, 0,0)
    val timeBusinessDaysAt21Pm = LocalDateTime.of(2022, Month.SEPTEMBER, 22, 21, 0,0)
    lateinit var localCall: Local

    @Test
    fun BillWithOneLocalCallInBusinessDaysBetween8an20hsSimple(){
        localCall = Local(timeBusinessDaysAt9Am)
        val call = Call(localCall, LocalTime.of(0,6,0))
        val bill = Bill()
        bill.addCall(call)
        assert(bill.getTotalCost() == 1.2)
    }
    @Test
    fun BillWithOneLocalCallInBusinessDaysBetween8an20hs(){
        localCall = Local(timeBusinessDaysAt9Am)
        val call = Call(localCall, LocalTime.of(1,5,55))
        val bill = Bill()
        bill.addCall(call)
        assert(bill.getTotalCost() == 13.18)
    }

    @Test
    fun BillWithOneLocalCallInBusinessDaysBetween20an8hs(){
        localCall = Local(timeBusinessDaysAt21Pm)
        val call = Call(localCall, LocalTime.of(2,5,15))
        val bill = Bill()
        bill.addCall(call)
        assert(bill.getTotalCost() == 12.53) //the exact total is 12,525 but rounder up
    }

    @Test
    fun BillWithOneLocalCallOf12Hours(){
        localCall = Local(timeBusinessDaysAt21Pm)
        val call = Call(localCall, LocalTime.of(12,5,15))
        val bill = Bill()
        bill.addCall(call)
        assert(bill.getTotalCost() == 79.05)
    }

    @Test
    fun BillWithOneNationalCallToCordoba(){
        val call = Call(cordobaCity, LocalTime.of(1,15,2))
        val bill = Bill()
        bill.addCall(call)
        assert(bill.getTotalCost() == 157.57)
    }

    @Test
    fun BillWithOneInternationalCallToUsa(){
        val call = Call(USA, LocalTime.of(0,15,42))
        val bill = Bill()
        bill.addCall(call)
        assert(bill.getTotalCost() == 86.35)
    }

    @Test
    fun OneBillWithMultiplesCalls(){
        val bill = Bill()
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
        assert(bill.getTotalCost() == 734.02)
    }

}