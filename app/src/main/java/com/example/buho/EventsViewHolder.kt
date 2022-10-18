package com.example.buho;

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.MEC_title)
    var state: TextView = itemView.findViewById(R.id.MEC_state)
    var classroom: TextView = itemView.findViewById(R.id.MEC_classroom)
    var schedule: TextView = itemView.findViewById(R.id.MEC_schedule)
    var description: TextView = itemView.findViewById(R.id.MEC_description)
    var layout: ConstraintLayout = itemView.findViewById(R.id.HC_cl_a)
}
