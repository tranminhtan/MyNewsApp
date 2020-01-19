package com.news.app.di

import androidx.lifecycle.ViewModel
import com.news.app.annotation.ActivityScoped
import com.news.app.annotation.ViewModelKey
import com.news.app.di.list.NewsListActivityModule
import com.news.app.ui.list.NewsListActivity
import com.news.app.ui.list.NewsListViewModel
import com.news.app.ui.list.OnArticleClickListener
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
interface ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [NewsListActivityModule::class])
    fun contributeRatesActivityInjector(): NewsListActivity

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    fun bindNewsListViewModel(
        viewModel: NewsListViewModel
    ): ViewModel

    @Binds
    fun bindOnArticleClickListener(newsListViewModel: NewsListViewModel): OnArticleClickListener
}