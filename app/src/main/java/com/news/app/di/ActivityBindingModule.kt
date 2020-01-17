package com.news.app.di

import com.news.app.annotation.ActivityScoped
import com.news.app.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeRatesActivityInjector(): MainActivity
}