package com.thepyprogrammer.nushievents.model

data class Event(
    val title: String,
    val dates: MutableList<TimeRange>,
    val description: String,
    val info: String
)
