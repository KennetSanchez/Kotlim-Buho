package com.example.buho.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buho.R
import com.example.buho.adapters.MyActivitiesListAdapter
import com.example.buho.adapters.MyEventsListAdapter
import com.example.buho.adapters.SuggestedActivityListAdapter
import com.example.buho.databinding.ActivitiesPageBinding
import com.example.buho.models.MyActivityCardComponent
import com.example.buho.models.MyEventCardComponent
import com.example.buho.models.SuggestedEventComponent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ActivitiesFragment(val main : ConstraintLayout) : Fragment(R.layout.activities_page) {
    private var _binding: ActivitiesPageBinding?=null
    private val binding get()=_binding!!

    private val myActivitiesAdapter = MyActivitiesListAdapter(this)
    private val activitiesAdapter = SuggestedActivityListAdapter(this)

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

        loadActivities()

        val activitiesRV = binding.AFSaRV
        activitiesRV.setHasFixedSize(true)
        activitiesRV.layoutManager = LinearLayoutManager(activity)
        activitiesRV.adapter = activitiesAdapter


        val cardsSuggestedEvents = binding.AFMaRV.children

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

    private fun loadActivities(){
        val activities = ArrayList<SuggestedEventComponent>()

        Firebase.firestore.collection("activities").get().addOnSuccessListener{
            for (document in it.documents) {
                val obj = document.toObject(SuggestedEventComponent::class.java)
                activities.add(obj!!)
            }
            Log.e(">>>", activities[0].toString())
        }


    }

    private fun createDummyInfo(){
        val card1 = MyActivityCardComponent(getString(R.string.AF_dummy_my_activities_title_1), getString(R.string.AF_dummy_my_activities_day_1), getString(
            R.string.AF_dummy_my_activities_classroom_1), getString(R.string.AF_dummy_my_activities_schedule_1), getString(R.string.AF_dummy_my_activities_teacher_1))

        val card2 = MyActivityCardComponent(getString(R.string.AF_dummy_my_activities_title_2), getString(R.string.AF_dummy_my_activities_day_2), getString(
            R.string.AF_dummy_my_activities_classroom_2), getString(R.string.AF_dummy_my_activities_schedule_2), getString(R.string.AF_dummy_my_activities_teacher_2))

        val card3 = MyActivityCardComponent(getString(R.string.AF_dummy_my_activities_title_3), getString(R.string.AF_dummy_my_activities_day_3), getString(
            R.string.AF_dummy_my_activities_classroom_3), getString(R.string.AF_dummy_my_activities_schedule_3), getString(R.string.AF_dummy_my_activities_teacher_3))

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
            details =  "Ven a disfrutar con Bienestar Universitario",
            speaker_type =  "Profesor: ",
            speaker_name =  dialogParams[4],
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
            dialogParams[1],
            dialogParams[2],
            dialogParams[3],
            dialogParams[4]
        )

        Firebase.firestore.collection("users").document(Firebase.auth.currentUser!!.uid).collection("interestedActivities")

        myActivitiesAdapter.addCard(newCard)
        myActivitiesAdapter.notifyDataSetChanged()
    }
}