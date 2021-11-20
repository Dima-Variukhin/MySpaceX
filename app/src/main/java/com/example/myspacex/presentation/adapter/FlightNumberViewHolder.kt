package com.example.myspacex.presentation.adapter

import android.view.View
import android.widget.TextView
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi

class FlightNumberViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.FlightNumber>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val value = (model as LaunchUi.FlightNumber).value
        textView.text = itemView.context.getString(R.string.flight_number, value)
    }
}