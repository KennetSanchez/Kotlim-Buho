package com.example.buho

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.buho.databinding.HomePageBinding

class HomeFragment : Fragment(R.layout.home_page) {
    private var _binding: HomePageBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= HomePageBinding.inflate(inflater,container, false)
        val view= binding.root;


        binding.HCClA.setOnClickListener{
            showDetails(it)
        }


        return view;
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
        binding.testText.text = dialogParams[0]

        val df : DetailsFragment = DetailsFragment(
            tittle = "T1",
            state =  "S1",
            classroom =  "C1",
            schedule =  "SC1",
            speaker_type =  "ST1",
            speaker_name =  "SN1",
            details =  "D1",
        )
    }
}