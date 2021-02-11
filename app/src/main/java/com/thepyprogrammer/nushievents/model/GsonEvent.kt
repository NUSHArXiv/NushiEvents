package com.thepyprogrammer.nushievents.model

import com.google.gson.annotations.SerializedName

data class GsonEvent(
        @SerializedName("title") val title: String,
        @SerializedName("date") val dates: MutableList<String>,
        @SerializedName("info") val info: String
) {
    fun toEvent(): Event {
        val desc = StringBuilder()
        dates.forEach {
            desc.append(it + "\n")
        }
        val event = Event(title, mutableListOf(), desc.toString(), info)
        dates.forEach {
            TimeRange.fromString(it)?.let { it1 -> event.dates.add(it1) }
        }
        return event
    }

}