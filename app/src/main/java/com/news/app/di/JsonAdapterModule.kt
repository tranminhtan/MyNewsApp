package com.news.app.di

import com.news.app.moshi.NewsJsonAdapterFactory
import com.squareup.moshi.JsonAdapter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object JsonAdapterModule {

    @JvmStatic
    @Provides
    @IntoSet
    fun provideJsonAdapterFactory(): JsonAdapter.Factory = NewsJsonAdapterFactory()
}
