package com.example.buho.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buho.R
import com.example.buho.adapters.MyActivitiesListAdapter
import com.example.buho.adapters.MyEventsListAdapter
import com.example.buho.databinding.ActivitiesPageBinding
import com.example.buho.models.MyActivityCardComponent
import com.example.buho.models.MyEventCardComponent
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivitiesFragment(val main : ConstraintLayout) : Fragment(R.layout.activities_page) {
    private var _binding: ActivitiesPageBinding?=null
    private val binding get()=_binding!!

    private val myActivitiesAdapter = MyActivitiesListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivitiesPageBinding.inflate(inflater,container, false)
        val view = binding.root;

        val myActivitiesRV = binding.AFMaRV
        myActivitiesRV.setHasFixedSize(true)
        myActivitiesRV.layoutManager = LinearLayoutManager(activity)
        myActivitiesRV.adapter = myActivitiesAdapter

        val cardsSuggestedEvents = binding.AFSACl.children

        cardsSuggestedEvents.forEach { child ->
            child as CardView
            val childCl = child[0]
            childCl.setOnClickListener{showDetails(childCl)}
        }

        if (myActivitiesAdapter.itemCount == 0) {
            createDummyInfo()
        }

        return view
    }


    private fun createDummyInfo(){
        val card1 = MyActivityCardComponent(getString(R.string.HF_dummy_my_events_title1), getString(R.string.HF_dummy_my_events_state1), getString(
            R.string.HF_dummy_my_events_classroom1
        ),
            getString(R.string.HF_dummy_my_events_schedule1), getString(R.string.HF_dummy_my_events_description1))

        val card2 = MyActivityCardComponent(getString(R.string.HF_dummy_my_events_title2), getString(R.string.HF_dummy_my_events_state2), getString(
            R.string.HF_dummy_my_events_classroom2
        ),
            getString(R.string.HF_dummy_my_events_schedule2), getString(R.string.HF_dummy_my_events_description2))

        val card3 = MyActivityCardComponent(getString(R.string.HF_dummy_my_events_title2), getString(R.string.HF_dummy_my_events_state2), getString(
            R.string.HF_dummy_my_events_classroom2
        ),
            getString(R.string.HF_dummy_my_events_schedule2), getString(R.string.HF_dummy_my_events_description2))

        myActivitiesAdapter.addCard(card1)
        myActivitiesAdapter.addCard(card2)
        myActivitiesAdapter.addCard(card3)
    }

    private fun showDetails(view : View){
        val cl = view as ConstraintLayout
        val textsArrays = cl.children

        val dialogParams : ArrayList<String> = ArrayList()
        textsArrays.forEach{
            val currentText = it as TextView
            if(currentText.text != ""){
                dialogParams.add(currentText.text as String)
            }
        }

        DetailsFragment(
            tittle = dialogParams[0],
            state =  dialogParams[1],
            classroom =  dialogParams[2],
            schedule =  dialogParams[3],
            details =  dialogParams[4],
            speaker_type =  "Ponente: ",
            speaker_name =  "John Doe",
            mainButtonText = "Seguir evento",
            onClickMethod = { imInterested(view) }
        ).show(parentFragmentManager, "details")
    }

    private fun imInterested(view : View) {

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
            "Próximamente",
            "ICESI",
            dialogParams[4], getString(R.string.HF_dummy_my_events_description2))

        myActivitiesAdapter.addCard(newCard)
        myActivitiesAdapter.notifyDataSetChanged()
    }
    
    private fun showDetails(view : View, buttonText : String, function_to_execute : () -> Unit){
        val cl = view as ConstraintLayout
        val textsArrays = cl.children

        val dialogParams : ArrayList<String> = ArrayList()
        textsArrays.forEach{
            val currentText = it as TextView

            if(currentText.text != ""){
                dialogParams.add(currentText.text as String)
            }
        }

        DetailsFragment(
            tittle = dialogParams[0],
            state =  dialogParams[1],
            classroom =  dialogParams[2],
            schedule =  dialogParams[3],
            speaker_type =  "Profesor: ",

            //Not implemented yet
            speaker_name =  "John Doe",

            details =  dialogParams[4],
            mainButtonText = buttonText,
            onClickMethod = function_to_execute
        ).show(parentFragmentManager, "details")
    }

    private fun goAssistance() {
        (main[0] as BottomNavigationView).selectedItemId = R.id.assistenceItem
    }

    private fun imHere(){
        goAssistance()
    }

    private fun imInterested(){
        goAssistance()
    }
}