package com.news.app.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.news.app.R
import com.news.app.ui.detail.NewsDetailActivity
import com.news.app.ui.list.support.ArticleItem
import com.news.app.ui.list.support.OnArticleClickListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.shimmer_main_layout.*
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var listener: OnArticleClickListener

    @Inject
    lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        articlesRecyclerView.adapter = adapter
        shimmerView.startShimmer()

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
        lifecycle.addObserver(viewModel)

        viewModel.articles.observe(this, Observer {
            shimmerView.stopShimmer()
            shimmerView.visibility = View.GONE
            adapter.submitList(it)
        })
        listener.onClickArticle.observe(this, Observer {
            navigateToNewsDetail(it)
        })
    }

    private fun navigateToNewsDetail(item: ArticleItem) {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra(NewsDetailActivity.EXTRA_ARTICLE, item)
        startActivity(intent)
    }
}
