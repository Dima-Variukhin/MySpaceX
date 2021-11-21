package com.example.myspacex.presentation.adapter

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchUi

class ImageViewHolder(view: View) : LaunchDetailViewHolder<LaunchUi.Image>(view) {
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    override fun bind(model: Any) {
        Glide.with(imageView)
            .load((model as LaunchUi.Image).value)
            // .placeholder(R.drawable.ic_launcher_foreground)
            //  .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}
