package com.news.app.di.list

import com.news.app.annotation.ActivityScoped
import com.news.app.ui.list.NewsListAdapter
import com.news.app.ui.list.support.OnArticleClickListener
import dagger.Module
import dagger.Provides

@Module
class NewsListActivityProvidesModule {

    @Provides
    @ActivityScoped
    fun provideAdapter(listener: OnArticleClickListener) = NewsListAdapter(listener)
}
