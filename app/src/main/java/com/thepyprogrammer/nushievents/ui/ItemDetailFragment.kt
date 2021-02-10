package com.thepyprogrammer.nushievents.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.thepyprogrammer.nushievents.R
import com.thepyprogrammer.nushievents.model.Database
import com.thepyprogrammer.nushievents.model.Event
import java.util.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: Event? = null;

    companion object {
        lateinit var content: String;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = Database.currentItem
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            val sc = Scanner(resources.openRawResource(R.raw.github_markdown))
            val scBuilder = StringBuilder()
            while(sc.hasNext()) {
                scBuilder.append(sc.nextLine())
            }

            val hub = rootView.findViewById<WebView>(R.id.item_detail)

            hub.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return false
                }
            }

            val builder = StringBuilder("<html>\n<head>\n<style>\n").append(scBuilder.toString()).append("\n</style>\n</head>\n\n<body>\n").append(it.info).append("</body>\n</html>")
            content = builder.toString()
            hub.loadDataWithBaseURL(null, content, "text/html", "utf-8", null)

        }

        return rootView
    }
}