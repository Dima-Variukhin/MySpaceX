package com.example.myspacex.presentation.adapter

import android.view.View
import android.widget.TextView
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi

class RocketViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.Rocket>(view) {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    override fun bind(model: Any) {
        val modelData = model as LaunchUi.Rocket
        var firstStage = ""
        modelData.firstStageData.forEach {
            firstStage += it.first + " " + it.second + "\n"
        }
        var secondStage = modelData.secondStage.block.toString() + "\n"
        modelData.secondStage.payloads.forEach {
            secondStage += it.manufacturer + "\n" +
                    it.nationality + "\n" +
                    it.payloadType + "\n" +
                    it.payloadMassKg + "\n" +
                    it.orbit + "\n"
        }
        val rocket =
            "\n" + modelData.value + "\n" + modelData.type + "\n" + firstStage + "\n" + secondStage
        textView.text = itemView.context.getString(R.string.rocket, rocket)
    }
}