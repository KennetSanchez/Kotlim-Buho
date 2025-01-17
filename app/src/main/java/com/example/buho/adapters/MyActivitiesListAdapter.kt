package com.example.buho.adapters

import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.R
import com.example.buho.fragments.ActivitiesFragment
import com.example.buho.fragments.DetailsFragment
import com.example.buho.models.MyActivityCardComponent
import com.example.buho.viewholders.ActivitiesViewHolder
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyActivitiesListAdapter (private val activities : ActivitiesFragment): RecyclerView.Adapter<ActivitiesViewHolder>() {

    var myActivitiesCards = ArrayList<MyActivityCardComponent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.my_activity_card, parent, false)
        return ActivitiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        val cardN = myActivitiesCards[position]
        holder.title.text = cardN.title
        holder.state.text = cardN.state
        holder.classroom.text = cardN.classroom
        holder.schedule.text = cardN.schedule
        holder.teacher.text = cardN.teacher
        holder.description.text = cardN.description

        holder.layout.setOnClickListener {
            DetailsFragment(
                tittle = cardN.title,
                date =  cardN.state,
                classroom =  cardN.classroom,
                schedule =  cardN.schedule,
                details =  cardN.description,
                speaker_type =  "Profesor",
                speaker_name =  cardN.teacher,
                mainButtonText = "Estoy aquí",
                onClickMethod = { imHere() }
            ).show(activities.parentFragmentManager, "details")
        }

    }

    private fun imHere(){
        (activities.main[1] as BottomNavigationView).selectedItemId = R.id.assistenceItem
    }

    override fun getItemCount(): Int {
        return myActivitiesCards.size
    }

    fun addCard(cardComponent: MyActivityCardComponent) {
        myActivitiesCards.add(cardComponent)
    }
}