package com.example.buho

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.databinding.MyEventCardBinding

@SuppressLint("ViewConstructor")
class MyEventCardComponent (
    private val tittle: String,
    private val state: String,
    private val classroom: String,
    private val schedule: String,
    private val description: String,
    context: Context,
) : View(context) {

    private var _binding: MyEventCardBinding?=null
    private val binding get()=_binding!!

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= MyEventCardBinding.inflate(inflater,container, true)
        val view= binding.root;

        binding.MECTittle.text = tittle
        binding.MECState.text = state
        binding.MECClassroom.text = classroom
        binding.MECSchedule.text = schedule
        binding.MECDescription.text = description


        return view;
    }


}