package com.news.app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.news.app.BuildConfig
import com.news.app.R
import com.news.app.network.NewsService
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var newsService: NewsService

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsService.getTopHeadlines(BuildConfig.API_KEY, Locale.getDefault().country)
            .map { it.articles }
            .subscribe({}, { Timber.d(it) })
    }
}
