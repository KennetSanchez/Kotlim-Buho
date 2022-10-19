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
import com.example.buho.R
import com.example.buho.databinding.ActivitiesPageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivitiesFragment(private val main : ConstraintLayout) : Fragment(R.layout.activities_page) {
    private var _binding: ActivitiesPageBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= ActivitiesPageBinding.inflate(inflater,container, false)
        val view= binding.root;

        val cardsMyActivities = binding.AFMACl.children

        cardsMyActivities.forEach { children ->
            children as CardView
            val childCl = children[0]
            childCl.setOnClickListener{showDetails(childCl, "Estoy aquí") { imHere() } }
        }

        val cardsSuggestedActivities = binding.AFSACl.children

        cardsSuggestedActivities.forEach { children ->
            children as CardView
            val childCl = children[0]
            childCl.setOnClickListener{showDetails(childCl, "Seguir actividad") { imInterested() } }
        }
        return view;
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