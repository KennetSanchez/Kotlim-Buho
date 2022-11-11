package com.example.buho.viewholders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.buho.R

class SuggestedEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.card_suggestion_title)
    var layout: ConstraintLayout = itemView.findViewById(R.id.card_suggestion_cl)
    var date: TextView = itemView.findViewById(R.id.card_suggestion_date)
    var classroom: TextView = itemView.findViewById(R.id.card_suggestion_classroom)
    var schedule: TextView = itemView.findViewById(R.id.card_suggestion_schedule)
    var teacher: TextView = itemView.findViewById(R.id.card_suggestion_professor)
    var description: TextView = itemView.findViewById(R.id.card_suggestion_description)
}
