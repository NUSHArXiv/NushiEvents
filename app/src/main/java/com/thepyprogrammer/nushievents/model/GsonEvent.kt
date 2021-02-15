package com.thepyprogrammer.nushievents.model

import com.google.gson.annotations.SerializedName
import com.thepyprogrammer.nushievents.model.time.TimeRange

data class GsonEvent(
    @SerializedName("title") val title: String,
    @SerializedName("date") val dates: MutableList<String>,
    @SerializedName("text") val content: String,
    @SerializedName("info") val info: String
) {
    fun toEvent(): Event {
        val event = Event(title, mutableListOf(), "", content, info)
        val desc = StringBuilder()
        dates.sort()
        dates.forEach { date ->
            TimeRange.fromString(date)?.let {
                event.dates.add(it)
                desc.append(it.toString() + "\n")
            }
        }
        event.description = desc.toString()
        return event
    }

}