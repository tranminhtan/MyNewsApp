package com.news.app.ui.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleItem(
    val title: String,
    val author: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val content: String
) : Parcelable