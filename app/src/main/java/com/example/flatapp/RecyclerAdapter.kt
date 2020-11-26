package com.example.flatapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val list: ArrayList<Item>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.id)
        val title = view.findViewById<TextView>(R.id.title)
        val location = view.findViewById<TextView>(R.id.location)
        val price = view.findViewById<TextView>(R.id.price)
        //val imagesUrl = view.findViewById<TextView>(R.id.url)


        init {
            view.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(view.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
                val intent = Intent(view.context, CardActivity::class.java)
                intent.putExtra("url", list[0].url)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = list[position].id.toString()
        holder.title.text = list[position].title
        holder.location.text = list[position].location
        holder.price.text = list[position].price.toString()
        //holder.imagesUrl.text = list[position].url
    }
}