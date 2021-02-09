package com.thepyprogrammer.nushievents.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    private val titles = mutableListOf(
            "CNY Celebration",
            "CNY Holidays",
            "W@W Festival",
            "Research Congress",
            "Games Day",
            "Student Leaders' Investiture",
            "SSEF Judging Day",
            "Good Friday",
            "Speech Day",
            "Y5&6 Reading Day",
            "Labour Day Holiday",
            "Y1-4 Reading Day",
            "Hari Raya Puasa",
            "SJPO General Round",
            "Vesak Day",
            "Issuance of Progress Reports",
            "'O'/'A' Level MTB Mid-Yr Exam",
            "SMO Junior/Senior Round I",
            "SMO Open Round I",
            "SMO Junior/Senior Round II",
            "SJPO Special Round",
            "Student Leaders' Summit",
            "SMO Open Round II",
            "'O'/'A' Level MT LC Exam",
            "Hari Raya Haji",
            "National Day Celebration",
            "Home Coming 2021",
            "National Day Holiday",
            "SDYC 2021",
            "StAR Day Celebration",
            "Teacher's Day",
            "Colourfusion VI"
    )

    private val content = mutableListOf(
            "CNY Celebration",
            "CNY Holidays",
            "W@W Festival",
            "Research Congress",
            "Games Day",
            "Student Leaders' Investiture",
            "SSEF Judging Day",
            "Good Friday",
            "Speech Day",
            "Y5&6 Reading Day",
            "Labour Day Holiday",
            "Y1-4 Reading Day",
            "Hari Raya Puasa",
            "SJPO General Round",
            "Vesak Day",
            "Issuance of Progress Reports and PTM",
            "GCE 'O'/'A' Level MTB Mid-Yr Exam",
            "SMO Junior/Senior Round I",
            "SMO Open Round I",
            "SMO Junior/Senior Round II",
            "SJPO Special Round",
            "Student Leaders' Summit",
            "SMO Open Round II",
            "GCE 'O'/'A' Level MT LC Exam",
            "Hari Raya Haji",
            "National Day Celebration",
            "Home Coming 2021",
            "National Day Holiday",
            "SDYC 2021",
            "StAR Day Celebration",
            "Teacher's Day",
            "Colourfusion VI"
    )

    private const val COUNT = 32

    init {
        // Add some sample items.
        for (i in 0 until COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP[item.title] = item
    }

    private fun createDummyItem(position: Int): DummyItem {
        return DummyItem(titles[position], content[position], makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val title: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}