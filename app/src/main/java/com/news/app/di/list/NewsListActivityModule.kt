package com.news.app.di.list

import com.news.app.annotation.ActivityScoped
import com.news.app.network.NewsService
import com.news.app.ui.list.NewsListAdapter
import com.news.app.ui.list.OnArticleClickListener
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

    @ActivityScoped
    @Provides
    fun provideNewsAdapter(articleClickListener: OnArticleClickListener) = NewsListAdapter(articleClickListener)
}
