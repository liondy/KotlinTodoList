package com.liondy.kotlintodolist

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import java.util.*


class ListAdapter(private val activity: Activity, private val context: Context) : BaseAdapter() {
    private val listItems: MutableList<String>

    fun add(newTodo: String) {
        listItems.add(newTodo)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listItems.size
    }

    override fun getItem(position: Int): Any {
        return listItems[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        i: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView: View? = convertView
        val todo = getItem(i) as String
        val vh: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_list, parent, false)
            val item = convertView.findViewById<View>(R.id.item_list) as TextView
            val trash = convertView.findViewById<ImageButton>(R.id.trash_item) as ImageButton
            vh = ViewHolder(item, trash)
            convertView.tag = vh
        } else {
            vh = convertView.tag as ViewHolder
        }
        vh.updateView(todo, i)
        return convertView
    }

    private inner class ViewHolder(private val item: TextView, private val trash: ImageButton) : View.OnClickListener {
        private var position = 0
        override fun onClick(v: View) {
            if (v.id == trash.id) {
                listItems.removeAt(position)
                notifyDataSetChanged()
            }
        }

        fun updateView(todo: String?, position: Int) {
            this.position = position
            item.text = todo
        }

        init {
            trash.setOnClickListener(this)
        }
    }

    init {
        listItems = ArrayList()
    }
}