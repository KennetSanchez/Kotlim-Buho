package com.example.buho.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buho.R
import com.example.buho.activities.QrScanActivity
import com.example.buho.adapters.MyAssistancesAdapter
import com.example.buho.databinding.AssistancePageBinding
import com.example.buho.models.Assistance
import com.example.buho.models.SuggestedEventComponent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AssistanceFragment : Fragment(R.layout.assistance_page) {
    private var _binding: AssistancePageBinding?=null
    private val binding get()=_binding!!

    private val myAssistancesAdapter = MyAssistancesAdapter(this);


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= AssistancePageBinding.inflate(inflater,container, false)
        val view= binding.root;

        binding.qrScanBtn.setOnClickListener{
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }


        val myAssistanceRV = binding.ASMaRV
        myAssistanceRV.setHasFixedSize(true)
        myAssistanceRV.layoutManager = LinearLayoutManager(activity)
        myAssistanceRV.adapter = myAssistancesAdapter

        //load assistances from database. I'm begging, make a separate method. I'll behead you if you do otherwise

        loadAssistances()

        if (myAssistancesAdapter.itemCount == 0) {
            createDummyInfo()
        }
        return view;
    }
    private fun loadAssistances(){
        val id = Firebase.auth.currentUser?.uid
        val assistances = ArrayList<Assistance>()
        Firebase.firestore.collection("users").document(id!!).collection("assistanceActivities").get().addOnSuccessListener {
            //Log.e(">>>", "actividades ${it.documents.size}")
            for (document in it.documents) {
                val obj = document.toObject<Assistance>()
                assistances.add(obj!!)
            }
        }
        Firebase.firestore.collection("users").document(id!!).collection("assistanceEvents").get().addOnSuccessListener {
            //Log.e(">>>", "eventos ${it.documents.size}")
            for (document in it.documents) {
                val obj = document.toObject<Assistance>()
                assistances.add(obj!!)
            }
            myAssistancesAdapter.setDataSet(assistances)
            myAssistancesAdapter.notifyDataSetChanged()
            //Log.e(">>>", "load ${myAssistancesAdapter.itemCount}")
        }
    }

    private fun createDummyInfo(){
        val card1 = Assistance("",getString(R.string.AF_dummy_my_activities_title_1), getString(R.string.AF_dummy_my_activities_schedule_1))

        val card2 = Assistance("",getString(R.string.AF_dummy_my_activities_title_2), getString(R.string.AF_dummy_my_activities_schedule_2))

        val card3 = Assistance("",getString(R.string.AF_dummy_my_activities_title_3), getString(R.string.AF_dummy_my_activities_schedule_3))

        myAssistancesAdapter.addCard(card2)
        myAssistancesAdapter.addCard(card3)

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startForResult.launch(Intent(this.context, QrScanActivity::class.java))
            loadAssistances()
            //Log.e(">>>","entró al granted")
        } else {
            Toast.makeText(context, "Se necesita permiso para usar la cámara", Toast.LENGTH_SHORT).show()
        }
    }

    private val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
                if(result.resultCode== Activity.RESULT_OK){
                    //Log.e(">>>","entró al SFR")
                    loadAssistances()
                }
    }

}