package com.news.app.di

import android.app.Application
import androidx.room.Room
import com.news.app.BuildConfig
import com.news.app.provider.RetrofitProvider
import com.news.app.provider.SchedulersProvider
import com.news.app.provider.SchedulersProviderImpl
import com.news.app.db.NewsDao
import com.news.app.db.NewsDatabase
import com.news.app.moshi.NewsJsonAdapterFactory
import com.news.app.network.NewsService
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
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(NewsJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, schedulersProvider: SchedulersProvider): Retrofit {
        return RetrofitProvider(BuildConfig.NEWS_URL, moshi, schedulersProvider.io()).getRetrofit()
    }

    @Singleton
    @Provides
    fun provideDataBase(application: Application): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, BuildConfig.DB_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)


    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao()
}