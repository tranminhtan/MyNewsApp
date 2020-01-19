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
import kotlinx.android.synthetic.main.activity_news_list.articlesRecyclerView
import timber.log.Timber
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: NewsListAdapter

    private lateinit var disposable: Disposable

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        articlesRecyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
        viewModel.articles.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.navigateToDetailAction.observe(this, Observer {
            // Open Detail
        })

        disposable = Completable.mergeArray(viewModel.observeArticles(), viewModel.fetchArticles())
            .subscribe(Functions.EMPTY_ACTION, Consumer { Timber.e(it) })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
