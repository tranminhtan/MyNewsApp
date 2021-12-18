package com.news.app.di.list

import androidx.lifecycle.ViewModel
import com.news.app.annotation.ActivityScoped
import com.news.app.annotation.ViewModelKey
import com.news.app.repository.NewsListRepository
import com.news.app.repository.NewsListRepositoryImpl
import com.news.app.ui.list.NewsListViewModel
import com.news.app.ui.list.OnArticleClickListener
import com.news.app.ui.list.OnArticleClickListenerImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsListActivityModule {

    @Binds
    @ActivityScoped
    abstract fun bindsListener(listener: OnArticleClickListenerImpl): OnArticleClickListener

    @Binds
    abstract fun bindsRepository(repository: NewsListRepositoryImpl): NewsListRepository

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindNewsListViewModel(
        viewModel: NewsListViewModel
    ): ViewModel
}
