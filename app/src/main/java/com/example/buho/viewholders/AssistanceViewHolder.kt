package com.example.buho.viewholders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.R

class AssistanceViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.MASC_title)
    var schedule: TextView = itemView.findViewById(R.id.MASC_schedule)
    var layout: ConstraintLayout = itemView.findViewById(R.id.MASC_cl)
}