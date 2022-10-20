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
import com.example.buho.models.MyEventCardComponent
import com.example.buho.R
import com.example.buho.adapters.MyEventsListAdapter
import com.example.buho.databinding.HomePageBinding

class HomeFragment(val main : ConstraintLayout) : Fragment(R.layout.home_page) {
    private var _binding: HomePageBinding?=null
    private val binding get()=_binding!!

    private val myEventsAdapter = MyEventsListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomePageBinding.inflate(inflater,container, false)
        val view = binding.root;

        val myEventsRV = binding.HFMeRv
        myEventsRV.setHasFixedSize(true)
        myEventsRV.layoutManager = LinearLayoutManager(activity)
        myEventsRV.adapter = myEventsAdapter

        val cardsSuggestedEvents = binding.HPSECl.children

        cardsSuggestedEvents.forEach { child ->
            child as CardView
            val childCl = child[0]
            childCl.setOnClickListener{showDetails(childCl)}
        }

        if (myEventsAdapter.itemCount == 0) {
            createDummyInfo()
        }

        return view
    }


    private fun createDummyInfo(){
        val card1 = MyEventCardComponent(getString(R.string.HF_dummy_my_events_title1), getString(R.string.HF_dummy_my_events_state1), getString(
            R.string.HF_dummy_my_events_classroom1
        ),
            getString(R.string.HF_dummy_my_events_schedule1), getString(R.string.HF_dummy_my_events_description1))

        val card2 = MyEventCardComponent(getString(R.string.HF_dummy_my_events_title2), getString(R.string.HF_dummy_my_events_state2), getString(
            R.string.HF_dummy_my_events_classroom2
        ),
            getString(R.string.HF_dummy_my_events_schedule2), getString(R.string.HF_dummy_my_events_description2))

        val card3 = MyEventCardComponent(getString(R.string.HF_dummy_my_events_title3), getString(R.string.HF_dummy_my_events_state3), getString(
            R.string.HF_dummy_my_events_classroom3
        ),
                    getString(R.string.HF_dummy_my_events_schedule3), getString(R.string.HF_dummy_my_events_description3))

        myEventsAdapter.addCard(card1)
        myEventsAdapter.addCard(card2)
        myEventsAdapter.addCard(card3)
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

        val newCard = MyEventCardComponent(
            dialogParams[0],
            dialogParams[1],
            dialogParams[2],
            dialogParams[3],
            dialogParams[4],

            )
        myEventsAdapter.addCard(newCard)
        myEventsAdapter.notifyDataSetChanged()
    }
}