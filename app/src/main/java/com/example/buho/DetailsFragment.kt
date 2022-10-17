package com.example.buho

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.buho.databinding.DetailsBinding

class DetailsFragment constructor(
    private val tittle: String,
    private val state: String,
    private val classroom: String,
    private val schedule: String,
    private val speaker_type: String,
    private val speaker_name: String,
    private val details: String
) :  Fragment(R.layout.details) {

        private var _binding: DetailsBinding?=null
        private val binding get()=_binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding= DetailsBinding.inflate(inflater,container, false)
            val view= binding.root;

            binding.DETitle.text = tittle;
            binding.DEState.text = state;
            binding.DEClassroom.text = classroom;
            binding.DESchedule.text = schedule;
            binding.DESpeakerType.text = speaker_type;
            binding.DESpeakerName.text = speaker_name;
            binding.DEDetails.text = details;

            return view;
        }

}