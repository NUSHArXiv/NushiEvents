package com.thepyprogrammer.nushievents.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.thepyprogrammer.nushievents.R
import com.thepyprogrammer.nushievents.model.Database


/**
 * An activity representing a list of Events. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        database =
                if (Database.currentOccurence == null) Database(resources.openRawResource(R.raw.db)) else Database.currentOccurence!!

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(findViewById(R.id.item_list))
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this)
        if (Database.currentItem != null)
            Database.currentOccurence?.indexOf(Database.currentItem)?.let { layoutManager.scrollToPosition(it) }
        recyclerView.layoutManager = layoutManager
        val adapter = EventItemAdapter(this, database, twoPane)
        recyclerView.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(SwipeToOpenCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)

        var item: MenuItem = menu.findItem(R.id.action_info)
        var builder = SpannableStringBuilder("* About")
        // replace "*" with icon
        builder.setSpan(ImageSpan(this, R.drawable.ic_info), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        item.title = builder

        item = menu.findItem(R.id.action_contact)
        builder = SpannableStringBuilder("* Contact Me")
        // replace "*" with icon
        builder.setSpan(ImageSpan(this, R.drawable.ic_email), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        item.title = builder

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_info -> {
            val intent = Intent(this@ItemListActivity, AboutActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_contact -> {
            val email = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:h1810124@nushigh.edu.sg")
            }
            startActivity(Intent.createChooser(email, "Contact Creator..."))
            true
        }
        else -> false
    }


}