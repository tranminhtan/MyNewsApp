package com.news.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "article")
@JsonClass(generateAdapter = true)
data class Article(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "title")
    @Json(name = "title")
    val title: String,

    @ColumnInfo(name = "author")
    @Json(name = "author")
    val author: String? = "",

    @ColumnInfo(name = "description")
    @Json(name = "description")
    val description: String? = "",

    @ColumnInfo(name = "url")
    @Json(name = "url")
    val url: String? = "",

    @ColumnInfo(name = "image_url")
    @Json(name = "urlToImage")
    val imageUrl: String? = "",

    @ColumnInfo(name = "published_at")
    @Json(name = "publishedAt")
    val publishedAt: String? = "",

    @ColumnInfo(name = "content")
    @Json(name = "content")
    val content: String? = ""
)

data class ArticleItem(
    val id: Long,
    val title: String,
    val author: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val content: String
)