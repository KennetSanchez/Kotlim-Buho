package com.example.buho.viewholders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.R

class ActivitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.MAC_title)
    var state: TextView = itemView.findViewById(R.id.MAC_state)
    var classroom: TextView = itemView.findViewById(R.id.MAC_classroom)
    var schedule: TextView = itemView.findViewById(R.id.MAC_schedule)
    var teacher: TextView = itemView.findViewById(R.id.MAC_teacher)
    var layout: ConstraintLayout = itemView.findViewById(R.id.MAC_cl)
}
