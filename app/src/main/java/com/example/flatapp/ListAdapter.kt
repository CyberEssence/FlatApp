package com.example.flatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(val context: Context, val list:ArrayList<Item>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        val id = view.findViewById<TextView>(R.id.id)
        val title = view.findViewById<TextView>(R.id.title)
        //val location = view.findViewById<TextView>(R.id.description)
        val price = view.findViewById<TextView>(R.id.price)

        id.text = list[position].id.toString()
        title.text = list[position].title
        //location.text = list[position].location
        price.text = list[position].price.toString()
        return view
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}