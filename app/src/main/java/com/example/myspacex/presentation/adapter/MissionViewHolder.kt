package com.example.myspacex.presentation.adapter

import android.view.View
import android.widget.TextView
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi

class MissionNameViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.MissionName>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.MissionName).value
        textView.text = itemView.context.getString(R.string.mission_name, value)
    }
}