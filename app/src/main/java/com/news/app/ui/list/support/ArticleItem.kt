package com.news.app.ui.list.support

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleItem(
    val title: String,
    val author: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val content: String
) : Parcelable