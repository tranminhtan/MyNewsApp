package com.news.app.di.jsonfactory

import com.news.app.moshi.NewsJsonAdapterFactory
import com.squareup.moshi.JsonAdapter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class JsonAdapterModule {

    @Provides
    @IntoSet
    fun provideJsonAdapterFactory(): JsonAdapter.Factory = NewsJsonAdapterFactory()
}
