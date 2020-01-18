package com.news.app.network

import com.news.app.model.TopHeadlinesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopHeadlines(@Header("Authorization") authentication: String, @Query("country") countryCode: String):
            Single<TopHeadlinesResponse>
}