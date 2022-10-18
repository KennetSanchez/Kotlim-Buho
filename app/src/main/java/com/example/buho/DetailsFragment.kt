package com.example.buho

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_BLUR_BEHIND
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.findFragment
import com.example.buho.databinding.DetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailsFragment constructor(
    private val tittle: String,
    private val state: String,
    private val classroom: String,
    private val schedule: String,
    private val speaker_type: String,
    private val speaker_name: String,
    private val details: String
) :  DialogFragment(R.layout.details) {
        private var _binding: DetailsBinding?=null
        private val binding get()=_binding!!

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            FLAG_BLUR_BEHIND
            _binding = DetailsBinding.inflate(LayoutInflater.from(context))
            val view = binding.root;

//            val radius = 15f;
//
//            binding.DEBlurBackdrop.setBlurRadius(radius).setBlurEnabled(true) // <-- Currently not working

            binding.DETitle.text = tittle;
            binding.DEState.text = state;
            binding.DEClassroom.text = classroom;
            binding.DESchedule.text = schedule;
            binding.DESpeakerType.text = speaker_type;
            binding.DESpeakerName.text = speaker_name;
            binding.DEDetails.text = details;

            binding.DENotInterested.setOnClickListener() {
                dismiss()
            }

            binding.DEImHere.setOnClickListener() {
                imHere(it)
            }

            val builder = AlertDialog.Builder(requireActivity())
            builder.setView(view)

            val dialog = builder.create()
            dialog.window!!.setGravity(Gravity.CENTER_VERTICAL)
            return dialog
        }

        private fun imHere(view: View) {

            dismiss()

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, AssistanceFragment())
                commit()
            }
        }

}