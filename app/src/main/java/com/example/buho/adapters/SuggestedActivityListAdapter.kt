package com.example.buho.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.R
import com.example.buho.fragments.ActivitiesFragment
import com.example.buho.fragments.DetailsFragment
import com.example.buho.models.MyActivityCardComponent
import com.example.buho.models.SuggestedEventComponent
import com.example.buho.viewholders.SuggestedEventViewHolder

class SuggestedActivityListAdapter(
    private val activities: ActivitiesFragment,
    private val myActivitiesAdapter: MyActivitiesListAdapter
): RecyclerView.Adapter<SuggestedEventViewHolder>() {

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
        holder.teacher.text = cardN.professor
        holder.description.text = cardN.description

        holder.layout.setOnClickListener {
            DetailsFragment(
                tittle = cardN.title,
                date =  cardN.date,
                classroom =  cardN.classroom,
                schedule =  cardN.schedule,
                details =  cardN.description,
                speaker_type =  "Profesor",
                speaker_name =  cardN.professor,
                mainButtonText = "Estoy aquí",
                onClickMethod = { imHere(it) }
            ).show(activities.parentFragmentManager, "details")
        }

    }

    private fun imHere(view: View) {
        val cl = view as ConstraintLayout
        val textsArrays = cl.children

        val dialogParams : ArrayList<String> = ArrayList()
        textsArrays.forEach{
            val currentText = it as TextView

            if(currentText.text != ""){
                dialogParams.add(currentText.text as String)
            }
        }

        val newCard = MyActivityCardComponent(
            dialogParams[0],
            dialogParams[1],
            dialogParams[2],
            dialogParams[3],
            dialogParams[4]
        )

        //Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).collection("interestedActivities")

        myActivitiesAdapter.addCard(newCard)
        myActivitiesAdapter.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return myActivitiesCards.size
    }

    fun addCard(cardComponent: SuggestedEventComponent) {
        myActivitiesCards.add(cardComponent)
    }

    fun setDataSet(newDataSet : ArrayList<SuggestedEventComponent>){
        myActivitiesCards = newDataSet
    }
}