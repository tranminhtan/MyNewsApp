package com.news.app.di.news

import com.news.app.annotation.ActivityScoped
import com.news.app.network.NewsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NewsListActivityModule {
    @ActivityScoped
    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}
