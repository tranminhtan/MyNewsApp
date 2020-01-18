package com.news.app.model

import com.squareup.moshi.Json

enum class Status {
    @Json(name = "ok")
    Ok,
    @Json(name = "error")
    Error
}