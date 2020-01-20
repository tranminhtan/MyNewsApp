package com.news.app.di.activity

import com.news.app.annotation.ActivityScoped
import com.news.app.di.list.NewsListActivityBindsModule
import com.news.app.di.list.NewsListActivityProvidesModule
import com.news.app.ui.list.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
interface ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // Kotlin class can't have both static and abstract (@Provides and @Binds)
            NewsListActivityBindsModule::class,
            NewsListActivityProvidesModule::class
        ]
    )
    fun contributeRatesActivityInjector(): NewsListActivity
}