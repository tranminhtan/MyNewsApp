package com.news.app.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

// This Set can gather all Factories by using Dagger's Multi binding as each module has one Factory
class MoshiProvider(private val factories: Set<JsonAdapter.Factory>) {

    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .apply {
                factories.forEach {
                    add(it)
                }
            }
            .build()
    }
}