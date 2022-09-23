package scr.places

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class Local(var date: LocalDateTime) : Place {
    override val placeName: String = "Local"
    override fun cost(time: LocalTime): Double {
        val dayOfWeek = date.dayOfWeek
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY ){
            return getCostOf(time, 0.10)
        }else{
            return getBusinessDaysCost(time)
        }
    }

    private fun getBusinessDaysCost(time: LocalTime): Double {
        val eightAM = LocalDateTime.of(date.year, date.month, date.dayOfMonth, 8,0,0)
        val eightPM = LocalDateTime.of(date.year, date.month, date.dayOfMonth, 20,0,0)

        val minutesBetween8and20 = getMinutesBetween(time, eightAM, eightPM)
        val minutesBetween20and8 = getMinutesBetween(time, eightPM, eightAM.plusDays(1))

        return getCostOf(minutesBetween8and20, 0.20) + getCostOf(minutesBetween20and8, 0.10)
    }

    private fun getMinutesBetween(time:LocalTime, date1: LocalDateTime, date2: LocalDateTime): LocalTime {
        val endCall = plusTimeAtDate(time)
        var timeBetween = LocalTime.of(0,0,0)

        if (date > date1 && endCall < date2 && date < endCall){
            return time
        }
        if (date > date1 && date < date2){
            timeBetween = minusDateAtTime(date2, date)
        }
        if (endCall.toLocalTime() < date2.toLocalTime() && endCall.toLocalTime() > date1.toLocalTime()){
            timeBetween = plusTimeAtTime(timeBetween, minusDateAtTime(endCall, date1))
        }
        return timeBetween
    }

    private fun plusTimeAtDate(time: LocalTime): LocalDateTime{
        val hours = time.hour.toLong()
        val minutes = time.minute.toLong()
        val seconds = time.second.toLong()
        return date.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds)
    }

    private fun minusDateAtTime(toSubtract: LocalDateTime, time: LocalDateTime): LocalTime{
        val hours = time.hour.toLong()
        val minutes = time.minute.toLong()
        val seconds = time.second.toLong()
        return toSubtract.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).toLocalTime()
    }

    private fun plusTimeAtTime(toAdd: LocalTime, time: LocalTime): LocalTime{
        val hours = time.hour.toLong()
        val minutes = time.minute.toLong()
        val seconds = time.second.toLong()
        return toAdd.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds)
    }
}