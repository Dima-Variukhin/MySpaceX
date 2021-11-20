package com.example.myspacex.presentation.adapter

import android.view.View
import android.widget.ImageView
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi
import com.squareup.picasso.Picasso

class ImageViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.Image>(view) {
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    override fun bind(model: Any) = Picasso.get()
        .load((model as LaunchUi.Image).value)
        .into(imageView)
}
