package com.news.app.di.activity

import com.news.app.annotation.ActivityScoped
import com.news.app.di.detail.NewsDetailActivityModule
import com.news.app.di.list.NewsListActivityModule
import com.news.app.ui.detail.NewsDetailActivity
import com.news.app.ui.list.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [NewsListActivityModule::class])
    fun contributeListActivityInjector(): NewsListActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [NewsDetailActivityModule::class])
    fun contributeDetailInjector(): NewsDetailActivity
}