import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

class BillTest {
    var localCallInBusinessDaysAt9Am = Local(LocalDateTime.of(2022, Month.SEPTEMBER, 22, 9, 0,0))

    @Test
    fun BillWithOneLocalCallInBusinessDaysBetween8an20hs(){
        val call = Call(localCallInBusinessDaysAt9Am, LocalTime.of(0,5,0))
        val bill = Bill()
        bill.addCall(call)
        assert(bill.getTotalCost() == 1.0)
    }
}