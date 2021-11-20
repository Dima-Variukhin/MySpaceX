package com.example.myspacex.presentation.adapter

import android.view.View
import android.widget.TextView
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi

class LaunchDateViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchDate>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.LaunchDate).value
        textView.text = itemView.context.getString(R.string.launch_date, value)
    }
}