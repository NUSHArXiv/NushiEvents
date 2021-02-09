package com.thepyprogrammer.nushievents.model

import java.time.LocalDate
import java.time.LocalTime
import java.util.regex.*

class TimeRange(
    val date: LocalDate,
    val start: LocalTime,
    val end: LocalTime
    ) {

    companion object {
        fun fromString(s: String): TimeRange? {
            val p =
                Pattern.compile("((\\d{2})/(\\d{2})/(\\d{4})) ((\\d{2})(\\d{2})) - ((\\d{2})(\\d{2}))")
            val m = p.matcher(s)
            if (!m.matches()) return null
            val date = LocalDate.of(m.group(4).toInt(), m.group(3).toInt(), m.group(2).toInt())
            val start = LocalTime.of(m.group(6).toInt(), m.group(7).toInt())
            val end = LocalTime.of(m.group(9).toInt(), m.group(10).toInt())
            return TimeRange(date, start, end);
        }
    }

    override fun toString(): String {
        return "${date.dayOfMonth}/${date.monthValue}/${date.year} ${start.hour}${start.minute} ${end.hour}${end.minute}"
    }
}