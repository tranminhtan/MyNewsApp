package com.news.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "article")
@JsonClass(generateAdapter = true)
data class Article(
    @ColumnInfo(name = "title")
    @Json(name = "title")
    val title: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = title.hashCode(),

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
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val content: String
)

fun Article.toArticleItem() =
    ArticleItem(
        id = id,
        title = title,
        author = author ?: "",
        description = description ?: "",
        url = url ?: "",
        imageUrl = imageUrl ?: "",
        content = content ?: ""
    )