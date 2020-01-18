package com.news.app.di

import com.news.app.annotation.ActivityScoped
import com.news.app.di.news.NewsListActivityModule
import com.news.app.ui.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [NewsListActivityModule::class])
    fun contributeRatesActivityInjector(): NewsListActivity
}