package com.news.app.network

import com.news.app.BuildConfig
import com.news.app.model.TopHeadlinesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("Authorization: ${BuildConfig.API_KEY}")
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") countryCode: String):
            Single<TopHeadlinesResponse>
}