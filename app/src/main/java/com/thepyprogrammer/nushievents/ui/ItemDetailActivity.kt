package com.thepyprogrammer.nushievents.ui

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thepyprogrammer.nushievents.R
import com.thepyprogrammer.nushievents.model.Database
import java.time.LocalTime
import java.util.*


/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Database.currentItem?.dates?.forEach {
                val (date, begin, end) = it

                val allDay = begin == LocalTime.of(0, 0) && end == LocalTime.of(23, 59)

                val startTime = date.atTime(begin)
                val endTime = date.atTime(end)

                val startMillis: Long = Calendar.getInstance().run {
                    set(startTime.year, startTime.monthValue - 1, startTime.dayOfMonth, startTime.hour, startTime.minute)
                    timeInMillis
                }
                val endMillis: Long = Calendar.getInstance().run {
                    set(endTime.year, endTime.monthValue - 1, endTime.dayOfMonth, endTime.hour, endTime.minute)
                    timeInMillis
                }

                val calIntent = Intent(Intent.ACTION_INSERT).apply {
                    type = "vnd.android.cursor.item/event"
                    data = Events.CONTENT_URI
                    putExtra(Events.TITLE, Database.currentItem?.title)
                    putExtra(Events.ALL_DAY, allDay)
                    putExtra(Events.DESCRIPTION, Database.currentItem?.content)
                    putExtra(
                            CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                            // date.atTime(begin).atZone(ZoneId.of("Singapore/Singapore")).toEpochSecond()
                            startMillis
                    )
                    putExtra(
                            CalendarContract.EXTRA_EVENT_END_TIME,
                            // date.atTime(end).atZone(ZoneId.of("Singapore/Singapore")).toEpochSecond()
                            endMillis
                    )
                    putExtra(Events.EVENT_LOCATION, getString(R.string.nushAddress))
                }

                startActivity(calIntent)
            }
//
//            val email = Intent(Intent.ACTION_SEND)
//            email.putExtra(Intent.EXTRA_SUBJECT, Database.currentItem?.title)
//            // email.putExtra(Intent.EXTRA_TEXT, Database.currentItem?.title)
//            // email.putExtra(Intent.EXTRA_HTML_TEXT, Html.fromHtml(ItemDetailFragment.content, Html.FROM_HTML_MODE_LEGACY))
//
//            //
//
//            email.putExtra(Intent.EXTRA_TEXT, HtmlCompat.fromHtml(StringBuilder("<!DOCTYPE html>\n<html>\n<body>\n").append(Database.currentItem?.info).append("</body>\n</html>").toString(), HtmlCompat.FROM_HTML_MODE_LEGACY))
//
//            //need this to prompts email client only
//            email.type = "message/rfc822"
//
//            startActivity(Intent.createChooser(email, "Send ${Database.currentItem?.title} to..."))
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don"t need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //

        val item = Database.currentItem
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = item?.title

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ItemDetailFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, ItemListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}