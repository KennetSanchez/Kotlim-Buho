package com.example.buho.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.R
import com.example.buho.fragments.ActivitiesFragment
import com.example.buho.fragments.DetailsFragment
import com.example.buho.models.SuggestedEventComponent
import com.example.buho.viewholders.SuggestedEventViewHolder
import com.google.android.material.bottomnavigation.BottomNavigationView

class SuggestedActivityListAdapter (private val activities : ActivitiesFragment): RecyclerView.Adapter<SuggestedEventViewHolder>() {

    var myActivitiesCards = ArrayList<SuggestedEventComponent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedEventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.suggested_card, parent, false)
        return SuggestedEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuggestedEventViewHolder, position: Int) {
        val cardN = myActivitiesCards[position]
        holder.title.text = cardN.title
        holder.date.text = cardN.date
        holder.classroom.text = cardN.classroom
        holder.schedule.text = cardN.schedule
        holder.teacher.text = cardN.teacher
        holder.description.text = cardN.description

        holder.layout.setOnClickListener {
            DetailsFragment(
                tittle = cardN.title,
                state =  cardN.date,
                classroom =  cardN.classroom,
                schedule =  cardN.schedule,
                details =  cardN.description,
                speaker_type =  R.string.MAC_speaker_type.toString(),
                speaker_name =  cardN.teacher,
                mainButtonText = R.string.MAC_button_text.toString(),
                onClickMethod = { imHere() }
            ).show(activities.parentFragmentManager, "details")
        }

    }

    private fun imHere(){
        (activities.main[0] as BottomNavigationView).selectedItemId = R.id.assistenceItem
    }

    override fun getItemCount(): Int {
        return myActivitiesCards.size
    }

    fun addCard(cardComponent: SuggestedEventComponent) {
        myActivitiesCards.add(cardComponent)
    }
}