package com.news.app.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.news.app.R
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import timber.log.Timber
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var disposable: Disposable

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
        viewModel.articles.observe(this, Observer {
            Timber.d(it.toString())
        })
//        disposable = Completable.mergeArray(viewModel.fetchArticles(), viewModel.observeArticles())
//            .subscribe(Functions.EMPTY_ACTION, Consumer { Timber.e(it) })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
