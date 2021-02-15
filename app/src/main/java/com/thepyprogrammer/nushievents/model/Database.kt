package com.thepyprogrammer.nushievents.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.time.LocalDateTime
import java.util.*
import java.util.regex.Pattern

class Database internal constructor(jsonStream: InputStream) : ArrayList<Event>() {
    companion object {
        var currentOccurence: Database? = null
        var currentItem: Event? = null
    }


    init {
        val sc = Scanner(jsonStream)
        val s = StringBuilder()
        while (sc.hasNext()) s.append(sc.nextLine())
        sc.close()
        val json = s.toString()
        val gson = Gson()
        val sType = object : TypeToken<List<GsonEvent>>() {}.type
        gson.fromJson<List<GsonEvent>>(json, sType)?.sortedBy {
            val end = it.dates[it.dates.size - 1]
            val day = end.split(Pattern.compile(" "), 2).toTypedArray()
            val date = day[0].split("/").toTypedArray().reversed().joinToString(separator = " ")
            date + day[1]
        }?.forEach {
            val ev = it.toEvent()
            val lastDate = ev.dates[ev.dates.size - 1]
            if (!lastDate.date.atTime(lastDate.end).isBefore(LocalDateTime.now())) add(ev)
        }
    }

}