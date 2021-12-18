package com.news.app.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopHeadlinesResponse(
    @Json(name = "status") val status: Status,
    @Json(name = "articles") val articles: List<Article>?
)
