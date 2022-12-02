package com.example.buho.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.R
import com.example.buho.fragments.AssistanceFragment
import com.example.buho.models.Assistance
import com.example.buho.viewholders.AssistanceViewHolder

class MyAssistancesAdapter (private val activities : AssistanceFragment): RecyclerView.Adapter<AssistanceViewHolder>() {

    var myAssistancesCards = ArrayList<Assistance>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssistanceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.my_assistence_card, parent, false)
        return AssistanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssistanceViewHolder, position: Int) {
        val cardN = myAssistancesCards[position]
        holder.title.text = cardN.id
        holder.schedule.text = cardN.date
    }

    override fun getItemCount(): Int {
        return myAssistancesCards.size
    }

    fun addCard(cardComponent: Assistance) {
        myAssistancesCards.add(cardComponent)
    }


}