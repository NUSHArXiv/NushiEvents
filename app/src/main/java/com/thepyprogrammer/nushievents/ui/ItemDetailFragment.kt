package com.thepyprogrammer.nushievents.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.thepyprogrammer.nushievents.R
import com.thepyprogrammer.nushievents.model.Database
import com.thepyprogrammer.nushievents.model.Event
import io.noties.markwon.Markwon
import us.feras.mdv.MarkdownView

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = Database.currentItem
//        activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = item?.title

//        arguments?.let {
//            if (it.containsKey(ARG_ITEM_ID)) {
//                // Load the dummy content specified by the fragment
//                // arguments. In a real-world scenario, use a Loader
//                // to load content from a content provider.
//                    val database = if (Database.currentOccurence == null) Database(resources.openRawResource(R.raw.db)) else Database.currentOccurence!!
//                item = database.itemMap[it.getString(ARG_ITEM_ID)]
//                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = item?.title
//
//            }
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            val markwon = context?.let { it1 ->
                Markwon.create(it1)
            }

            markwon?.setMarkdown(rootView.findViewById(R.id.item_detail), it.info)

        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}