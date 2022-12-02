package com.example.buho.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buho.R
import com.example.buho.activities.QrScanActivity
import com.example.buho.adapters.MyAssistancesAdapter
import com.example.buho.databinding.AssistancePageBinding
import com.example.buho.models.Assistance

class AssistanceFragment : Fragment(R.layout.assistance_page) {
    private var _binding: AssistancePageBinding?=null
    private val binding get()=_binding!!
    lateinit var assistance: Assistance

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


        if (arguments != null) {
            assistance = (arguments!!.getSerializable("Assistance") as Assistance?)!!
        }
        //state


        val myAssistanceRV = binding.ASMaRV
        myAssistanceRV.setHasFixedSize(true)
        myAssistanceRV.layoutManager = LinearLayoutManager(activity)
        myAssistanceRV.adapter = myAssistancesAdapter

        //load assistances from database. I'm begging, make a separate method. I'll behead you if you do otherwise


        if (myAssistancesAdapter.itemCount == 0) {
            createDummyInfo()
        }
        return view;
    }

    private fun createDummyInfo(){
        val card1 = Assistance(getString(R.string.AF_dummy_my_activities_title_1), getString(R.string.AF_dummy_my_activities_schedule_1))

        val card2 = Assistance(getString(R.string.AF_dummy_my_activities_title_2), getString(R.string.AF_dummy_my_activities_schedule_2))

        val card3 = Assistance(getString(R.string.AF_dummy_my_activities_title_3), getString(R.string.AF_dummy_my_activities_schedule_3))


        myAssistancesAdapter.addCard(card1)
        myAssistancesAdapter.addCard(card2)
        myAssistancesAdapter.addCard(card3)

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startForResult.launch(Intent(this.context, QrScanActivity::class.java))

            //val intent = Intent(this.context, QrScanActivity::class.java)
            //startActivity(intent)
        } else {
            Toast.makeText(context, "Se necesita permiso para usar la cámara", Toast.LENGTH_SHORT).show()
        }
    }

    private val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
                if(result.resultCode== Activity.RESULT_OK){
                    myAssistancesAdapter.addCard(assistance)
                }
    }

}