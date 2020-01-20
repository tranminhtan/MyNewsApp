package com.news.app.moshi

import com.news.app.model.Article
import com.news.app.model.ArticleJsonAdapter
import com.news.app.model.TopHeadlinesResponse
import com.news.app.model.TopHeadlinesResponseJsonAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

// Manually implement the Factory rather than using KotlinJsonAdapterFactory for better performance
class NewsJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (annotations.isNotEmpty()) return null
        return when (Types.getRawType(type)) {
            TopHeadlinesResponse::class.java -> TopHeadlinesResponseJsonAdapter(moshi).nullSafe()
            Article::class.java -> ArticleJsonAdapter(moshi).nullSafe()

            else -> null
        }
    }

}
