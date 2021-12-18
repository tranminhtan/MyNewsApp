package com.news.app.di.detail

import com.news.app.ui.list.NewsListRepository
import com.news.app.ui.list.NewsListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NewsDetailActivityModule {

    @Binds
    abstract fun bindsRepository(repository: NewsListRepositoryImpl): NewsListRepository
}
