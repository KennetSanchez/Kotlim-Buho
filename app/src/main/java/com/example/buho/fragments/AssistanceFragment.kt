package com.example.buho.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.buho.R
import com.example.buho.activities.LoginActivity
import com.example.buho.activities.QrScanActivity
import com.example.buho.databinding.AssistancePageBinding

class AssistanceFragment : Fragment(R.layout.assistance_page) {
    private var _binding: AssistancePageBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= AssistancePageBinding.inflate(inflater,container, false)
        val view= binding.root;
        binding.qrScanBtn.setOnClickListener{
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
        binding.qrScanBtn.setOnClickListener{
            val intent = Intent(this.context, QrScanActivity::class.java)
            startActivity(intent)
        }
        return view;
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        } else {
            Toast.makeText(context, "Se necesita permiso para usar la cámara", Toast.LENGTH_SHORT).show()
        }
    }

    private val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
                if(result.resultCode== Activity.RESULT_OK){

                }
    }

}