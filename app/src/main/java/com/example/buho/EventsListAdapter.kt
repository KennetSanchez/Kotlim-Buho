package com.example.buho

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.databinding.MyEventCardBinding

class EventsListAdapter (
          private var itemList : List<MyEventCardComponent> ,
          private var context : Context
        ): RecyclerView.Adapter<EventsListAdapter.ViewHolder>() {

    private var _binding: MyEventCardBinding?=null
    private val binding get()=_binding!!


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(myEventCardComponent: MyEventCardComponent) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_event_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(itemList[position])
    }

    fun setItems(items: List<MyEventCardComponent>){
        itemList = items
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}