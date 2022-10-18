package com.example.buho

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buho.databinding.HomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment(private val main : ConstraintLayout) : Fragment(R.layout.home_page) {
    private var _binding: HomePageBinding?=null
    private val binding get()=_binding!!

    private val myEventsAdapter = EventsListAdapter()
    private var hasChildren = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomePageBinding.inflate(inflater,container, false)
        val view = binding.root;

        val cardsMyEvents = binding.AFMaRv.children

        cardsMyEvents.forEach { child ->
            child.setOnClickListener{showDetails(child, "Estoy aquí") { imHere() } }
        }


        val myEventsRV = binding.AFMaRv
        myEventsRV.setHasFixedSize(true)
        myEventsRV.layoutManager = LinearLayoutManager(activity)
        myEventsRV.adapter = myEventsAdapter

        val cardsSuggestedEvents = binding.HPSECl.children

        cardsSuggestedEvents.forEach { child ->
            child as CardView
            val childCl = child[0]
            childCl.setOnClickListener{showDetails(childCl, "Seguir evento") { imInterested(childCl) } }
        }

        if (myEventsRV.size == 0 && !hasChildren) {
            createDummyInfo()
            hasChildren = true
        }

        return view
    }


    private fun createDummyInfo(){
        val card1 = MyEventCardComponent(getString(R.string.HF_dummy_my_events_title1), getString(R.string.HF_dummy_my_events_state1), getString(R.string.HF_dummy_my_events_classroom1),
            getString(R.string.HF_dummy_my_events_schedule1), getString(R.string.HF_dummy_my_events_description1))

        val card2 = MyEventCardComponent(getString(R.string.HF_dummy_my_events_title2), getString(R.string.HF_dummy_my_events_state2), getString(R.string.HF_dummy_my_events_classroom2),
            getString(R.string.HF_dummy_my_events_schedule2), getString(R.string.HF_dummy_my_events_description2))

        val card3 = MyEventCardComponent(getString(R.string.HF_dummy_my_events_title2), getString(R.string.HF_dummy_my_events_state2), getString(R.string.HF_dummy_my_events_classroom2),
                    getString(R.string.HF_dummy_my_events_schedule2), getString(R.string.HF_dummy_my_events_description2))

        myEventsAdapter.addCard(card1)
        myEventsAdapter.addCard(card2)
        myEventsAdapter.addCard(card3)
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
            speaker_type =  "Ponente: ",
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

        val newCard = MyEventCardComponent(
            dialogParams[0],
            "Próximamente",
            "ICESI",
            dialogParams[4], getString(R.string.HF_dummy_my_events_description2))

        myEventsAdapter.addCard(newCard)
        myEventsAdapter.notifyDataSetChanged()
    }
}