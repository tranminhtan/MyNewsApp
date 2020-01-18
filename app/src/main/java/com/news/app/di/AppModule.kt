package com.news.app.di

import com.news.app.BuildConfig
import com.news.app.base.RetrofitProvider
import com.news.app.base.SchedulersProvider
import com.news.app.base.SchedulersProviderImpl
import com.news.app.moshi.MoshiProvider
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object AppModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideSchedulersProvider(): SchedulersProvider = SchedulersProviderImpl()

    @JvmStatic
    @Singleton
    @Provides
    fun provideMoshi(factories: Set<@JvmSuppressWildcards JsonAdapter.Factory>): Moshi {
        return MoshiProvider(factories).getMoshi()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, schedulersProvider: SchedulersProvider): Retrofit {
        return RetrofitProvider(BuildConfig.NEWS_URL, moshi, schedulersProvider.io()).createRetrofit()
    }
}