package com.thepyprogrammer.nushievents.ui.list

import android.content.Intent
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.nushievents.R
import com.thepyprogrammer.nushievents.model.Database
import com.thepyprogrammer.nushievents.model.Event
import com.thepyprogrammer.nushievents.ui.detail.ItemDetailActivity
import com.thepyprogrammer.nushievents.ui.detail.ItemDetailFragment

class SwipeToOpenCallback(
        private val adapter: EventItemAdapter,
        dragDirs: Int = 0,
        swipeDirs: Int = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val v = viewHolder.itemView
        val item = v.tag as Event
        Database.currentItem = item
        if (adapter.twoPane) {
            val fragment = ItemDetailFragment()
            adapter.parentActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(v.context, ItemDetailActivity::class.java)
            v.context.startActivity(intent)
        }
    }
}