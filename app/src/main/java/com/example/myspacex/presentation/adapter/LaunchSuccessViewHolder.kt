package com.example.myspacex.presentation.adapter

import android.view.View
import android.widget.CheckBox
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi

class LaunchSuccessViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.LaunchSuccess>(view) {
    private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    override fun bind(model: Any) {
        with(checkBox) {
            isChecked = (model as LaunchUi.LaunchSuccess).value
            setText(R.string.launch_success)
        }
    }
}