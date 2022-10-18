package com.example.buho

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.buho.databinding.FragmentAssistanceBinding

class AssistanceFragment : Fragment(R.layout.fragment_assistance) {
    private var _binding: FragmentAssistanceBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAssistanceBinding.inflate(inflater,container, false)
        val view= binding.root;
        binding.qrScanBtn.setOnClickListener{
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        return view;
    }

    private val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
                if(result.resultCode== Activity.RESULT_OK){

                }
    }

}