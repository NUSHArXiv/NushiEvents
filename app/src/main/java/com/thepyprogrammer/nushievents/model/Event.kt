package com.thepyprogrammer.nushievents.model

data class Event(
    val title: String,
    val dates: MutableList<TimeRange>,
    var description: String,
    val info: String
)
