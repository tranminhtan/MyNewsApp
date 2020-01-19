package com.news.app.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageFromUrl", "placeholder"])
fun bindImageFromUrl(imageView: ImageView, imageUrl: String?, placeholder: Drawable) {
    if (imageUrl.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(placeholder)
            .into(imageView)
    } else {
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(RequestOptions().placeholder(placeholder))
            .into(imageView)
    }
}