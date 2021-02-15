package com.thepyprogrammer.nushievents.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.nushievents.R
import com.thepyprogrammer.nushievents.model.Database
import com.thepyprogrammer.nushievents.model.Event

class EventItemAdapter(
    val parentActivity: ItemListActivity,
    private val values: List<Event>,
    val twoPane: Boolean
) :
    RecyclerView.Adapter<EventItemAdapter.ViewHolder>() {

    val onClickListener: View.OnClickListener = View.OnClickListener { v ->
        val item = v.tag as Event
        Database.currentItem = item
        if (twoPane) {
            val fragment = ItemDetailFragment()
            parentActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(v.context, ItemDetailActivity::class.java)
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.description

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.content)
        val contentView: TextView = view.findViewById(R.id.desc)
    }
}