import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class Local(var date: LocalDateTime) : Place{

    override fun cost(time: LocalTime): Double {
        val dayOfWeek = date.dayOfWeek
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY ){
            return getCostOf(time, 0.10)
        }else{
            return getBusinessDaysCost(time)
        }
    }

    private fun getBusinessDaysCost(time: LocalTime): Double {
        val eightAM = LocalTime.of(8,0,0)
        val eightPM = LocalTime.of(20,0,0)

        val minutesBetween8and20 = getMinutesBetween(time, eightAM, eightPM)
        val minutesBetween20and8 = getMinutesBetween(time, eightPM, eightAM)

        return getCostOf(minutesBetween8and20, 0.20) + getCostOf(minutesBetween20and8, 0.10)
    }


    private fun getMinutesBetween(time:LocalTime, date1: LocalTime, date2: LocalTime): LocalTime {
        val endCall = plusTimeAtDate(time).toLocalTime()
        val dateTime = date.toLocalTime()
        var timeBetween = LocalTime.of(0,0,0)

        if (dateTime > date1 && endCall < date2 && dateTime < endCall){
            return time
        }
        if (dateTime > date1){
            timeBetween = minusTimeAtTime(date2, dateTime)
        }
        if (endCall < date2){
            timeBetween = plusTimeAtTime(timeBetween, minusTimeAtTime(endCall, date1))
        }
        return timeBetween
    }

    private fun plusTimeAtDate(time: LocalTime): LocalDateTime{
        val hours = time.hour.toLong()
        val minutes = time.minute.toLong()
        val seconds = time.minute.toLong()
        return date.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds)
    }

    private fun minusTimeAtTime(toSubtract: LocalTime, time: LocalTime): LocalTime{
        val hours = time.hour.toLong()
        val minutes = time.minute.toLong()
        val seconds = time.minute.toLong()
        return toSubtract.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds)
    }

    private fun plusTimeAtTime(toAdd: LocalTime, time: LocalTime): LocalTime{
        val hours = time.hour.toLong()
        val minutes = time.minute.toLong()
        val seconds = time.minute.toLong()
        return toAdd.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds)
    }
}