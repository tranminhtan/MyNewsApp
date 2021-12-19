package com.news.app.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageFromUrl", "placeholder"])
fun bindImageFromUrl(imageView: ImageView, imageUrl: String?, placeholder: Drawable) {
    // To avoid using wrong drawable in RecyclerView
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

@BindingAdapter(value = ["bindAdapter", "data"])
fun <T> bindAdapter(recyclerView: RecyclerView, adapter: ListAdapter<T, *>, data: List<T>?) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = adapter
    }
    adapter.submitList(data)
}