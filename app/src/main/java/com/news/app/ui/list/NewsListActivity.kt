package com.news.app.ui.list

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.news.app.R
import com.news.app.ui.detail.NewsDetailActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import kotlinx.android.synthetic.main.activity_news_list.articlesRecyclerView
import kotlinx.android.synthetic.main.shimmer_main_layout.shimmerView
import timber.log.Timber
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var listener: OnArticleClickListener

    private lateinit var adapter: NewsListAdapter
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        adapter = NewsListAdapter(listener)
        articlesRecyclerView.adapter = adapter
        shimmerView.startShimmer()

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
        viewModel.articles.observe(this, Observer {
            shimmerView.stopShimmer()
            shimmerView.visibility = View.GONE
            adapter.submitList(it)
        })
        listener.onClickArticle.observe(this, Observer {
            navigateNewsDetail(it)
        })

        disposable = Completable.mergeArray(viewModel.observeArticles(), viewModel.fetchArticles())
            .subscribe(Functions.EMPTY_ACTION, Consumer { Timber.e(it) })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun navigateNewsDetail(item: ArticleItem) {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra(NewsDetailActivity.EXTRA_ARTICLE, item)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent)
        } else {
            startActivity(intent)
        }
    }
}
