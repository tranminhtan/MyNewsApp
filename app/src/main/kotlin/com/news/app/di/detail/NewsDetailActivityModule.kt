package com.news.app.di.detail

import androidx.lifecycle.ViewModel
import com.news.app.annotation.ViewModelKey
import com.news.app.repository.NewsListRepository
import com.news.app.repository.NewsListRepositoryImpl
import com.news.app.ui.detail.NewsDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsDetailActivityModule {

    @Binds
    abstract fun bindsRepository(repository: NewsListRepositoryImpl): NewsListRepository

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel::class)
    abstract fun bindNewsListViewModel(viewModel: NewsDetailViewModel): ViewModel
}
