package com.news.app.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter(value = ["imageFromUrl", "placeholder"])
fun bindImageFromUrl(view: ImageView, imageUrl: String?, placeholder: Drawable?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}