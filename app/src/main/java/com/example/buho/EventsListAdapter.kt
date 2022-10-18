package com.example.buho

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EventsListAdapter : RecyclerView.Adapter<EventsViewHolder>() {

    val cards = ArrayList<MyEventCardComponent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.my_event_card, parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val cardN = cards[position]

        holder.title.text = cardN.title
        holder.state.text = cardN.state
        holder.classroom.text = cardN.classroom
        holder.schedule.text = cardN.schedule
        holder.description.text = cardN.description
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun addCard(cardComponent: MyEventCardComponent) {
        cards.add(cardComponent)
    }
}