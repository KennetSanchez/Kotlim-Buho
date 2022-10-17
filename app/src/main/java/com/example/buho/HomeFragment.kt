package com.example.buho

import android.os.Bundle
import android.view.InputEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        

        return view;
    }

    fun showDetails(source : View){


    }
}