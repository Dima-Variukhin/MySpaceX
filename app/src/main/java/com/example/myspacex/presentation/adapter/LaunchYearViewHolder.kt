package com.example.myspacex.presentation.adapter

import android.view.View
import android.widget.TextView
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi

class LaunchYearViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchYear>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.LaunchYear).value
        textView.text = itemView.context.getString(R.string.launch_year, value)
    }
}