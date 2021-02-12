package com.thepyprogrammer.nushievents.ui

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thepyprogrammer.nushievents.R
import com.thepyprogrammer.nushievents.model.Database
import java.io.File
import java.io.PrintWriter
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

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

                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"
                intent.putExtra("beginTime", date.atTime(begin).toEpochSecond(ZoneOffset.ofHours(8)))
                intent.putExtra("allDay", allDay)
                intent.putExtra("endTime", date.atTime(end).toEpochSecond(ZoneOffset.ofHours(8)))
                intent.putExtra("title", Database.currentItem?.title)

                startActivity(intent)
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