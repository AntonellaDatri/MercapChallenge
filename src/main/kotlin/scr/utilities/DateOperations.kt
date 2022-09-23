package scr.utilities

import java.time.LocalDateTime
import java.time.LocalTime

class DateOperations private constructor() {

    companion object {
        fun plusTimeAtDate(time: LocalTime, date: LocalDateTime): LocalDateTime {
            val hours = time.hour.toLong()
            val minutes = time.minute.toLong()
            val seconds = time.second.toLong()
            return date.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds)
        }
    
        fun minusDateAtTime(toSubtract: LocalDateTime, time: LocalDateTime): LocalTime {
            val hours = time.hour.toLong()
            val minutes = time.minute.toLong()
            val seconds = time.second.toLong()
            return toSubtract.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).toLocalTime()
        }
    
        fun plusTimeAtTime(toAdd: LocalTime, time: LocalTime): LocalTime {
            val hours = time.hour.toLong()
            val minutes = time.minute.toLong()
            val seconds = time.second.toLong()
            return toAdd.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds)
        }
    }
}