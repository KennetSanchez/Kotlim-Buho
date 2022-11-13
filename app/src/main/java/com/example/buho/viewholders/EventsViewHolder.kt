package com.example.buho.viewholders;

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.R

class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.MEC_title)
    var date: TextView = itemView.findViewById(R.id.MEC_state)
    var classroom: TextView = itemView.findViewById(R.id.MEC_classroom)
    var schedule: TextView = itemView.findViewById(R.id.MEC_schedule)
    var description: TextView = itemView.findViewById(R.id.MEC_description)
    var layout: ConstraintLayout = itemView.findViewById(R.id.MEC_cl)
}
