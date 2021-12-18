package com.news.app.di.detail

import com.news.app.repository.NewsListRepository
import com.news.app.repository.NewsListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NewsDetailActivityModule {

    @Binds
    abstract fun bindsRepository(repository: NewsListRepositoryImpl): NewsListRepository
}
