package com.example.buho

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buho.databinding.FragmentAssistanceBinding
import java.util.jar.Manifest

class AssistanceFragment: Fragment() {
    private var _binding: FragmentAssistanceBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAssistanceBinding.inflate(inflater,container, false)
        val view= binding.root;

        requestPermissions(arrayOf(
            android.Manifest.permission.CAMERA,
        ),1)
        return view;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance()= AssistanceFragment()
    }

}