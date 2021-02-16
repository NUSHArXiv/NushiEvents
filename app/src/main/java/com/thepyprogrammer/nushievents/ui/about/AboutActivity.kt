package com.thepyprogrammer.nushievents.ui.about

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
import com.thepyprogrammer.nushievents.R
import com.thepyprogrammer.nushievents.ui.ItemListActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            R.id.action_info -> {
                val intent = Intent(this@AboutActivity, AboutActivity::class.java)
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
            else -> super.onOptionsItemSelected(item)
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
        builder.setSpan(
            ImageSpan(this, R.drawable.ic_email),
            0,
            1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        item.title = builder

        return true
    }
}