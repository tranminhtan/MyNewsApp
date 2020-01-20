package com.news.app.di.list

import androidx.lifecycle.ViewModel
import com.news.app.annotation.ActivityScoped
import com.news.app.annotation.ViewModelKey
import com.news.app.ui.list.NewsListViewModel
import com.news.app.ui.list.support.OnArticleClickListener
import com.news.app.ui.list.support.OnArticleClickListenerImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsListActivityBindsModule {

    @Binds
    @ActivityScoped
    abstract fun bindListener(listener: OnArticleClickListenerImpl): OnArticleClickListener

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun bindNewsListViewModel(
        viewModel: NewsListViewModel
    ): ViewModel
}
