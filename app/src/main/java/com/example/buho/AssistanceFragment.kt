package com.example.buho

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buho.databinding.FragmentAssistanceBinding

class AssistanceFragment: Fragment() {
    private lateinit var _binding: FragmentAssistanceBinding
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAssistanceBinding.inflate(inflater,container, false)
        val view= binding.root;
        return view;
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance()= AssistanceFragment()
    }
}