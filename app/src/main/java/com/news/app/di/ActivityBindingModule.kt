package com.news.app.di

import com.news.app.annotation.ActivityScoped
import com.news.app.di.list.NewsListActivityModule
import com.news.app.ui.list.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
interface ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            NewsListActivityModule::class
        ]
    )
    fun contributeRatesActivityInjector(): NewsListActivity
}