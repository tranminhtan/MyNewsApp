package com.news.app.di

import androidx.room.Room
import com.news.app.BuildConfig
import com.news.app.MyApplication
import com.news.app.base.RetrofitProvider
import com.news.app.base.SchedulersProvider
import com.news.app.base.SchedulersProviderImpl
import com.news.app.dao.NewsDao
import com.news.app.dao.NewsDatabase
import com.news.app.moshi.MoshiProvider
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideSchedulersProvider(): SchedulersProvider = SchedulersProviderImpl()

    @Singleton
    @Provides
    fun provideMoshi(factories: Set<@JvmSuppressWildcards JsonAdapter.Factory>): Moshi {
        return MoshiProvider(factories).getMoshi()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, schedulersProvider: SchedulersProvider): Retrofit {
        return RetrofitProvider(BuildConfig.NEWS_URL, moshi, schedulersProvider.io()).createRetrofit()
    }

    @Singleton
    @Provides
    fun provideDataBase(application: MyApplication): NewsDao {
        return Room.databaseBuilder(application, NewsDatabase::class.java, BuildConfig.DB_NAME).build().newsDao()
    }
}