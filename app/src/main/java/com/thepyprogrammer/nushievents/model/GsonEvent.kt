package com.thepyprogrammer.nushievents.model

import com.google.gson.annotations.SerializedName

data class GsonEvent(
    @SerializedName("title") val title: String,
    @SerializedName("date") val dates: MutableList<String>,
    @SerializedName("info") val info: String
) {
    fun toEvent(): Event {
        val event = Event(title, mutableListOf(), "", info)
        val desc = StringBuilder()
        dates.forEach { date ->
            TimeRange.fromString(date)?.let {
                event.dates.add(it)
                desc.append(it + "\n")
            }
        }
        return event
    }

}