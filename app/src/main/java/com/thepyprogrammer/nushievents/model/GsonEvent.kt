package com.thepyprogrammer.nushievents.model

import com.google.gson.annotations.SerializedName

data class GsonEvent(
    @SerializedName("title") val title: String,
    @SerializedName("date") val dates: MutableList<String>,
    @SerializedName("description") val description: String,
    @SerializedName("info") val info: String
) {
    fun toEvent(): Event {
        val desc = StringBuilder(description)
        dates.forEach {
            desc.append(it+"\n")
        }
        val event = Event(title, mutableListOf<TimeRange>(), desc.toString(), info)
        dates.forEach {
            TimeRange.fromString(it)?.let { it1 -> event.dates.add(it1) }
        }
        return event
    }

}