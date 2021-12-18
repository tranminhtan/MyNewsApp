package com.news.app.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.news.app.databinding.ActivityNewsListBinding
import com.news.app.model.ArticleItem
import com.news.app.ui.detail.NewsDetailActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var listener: OnArticleClickListener

    @Inject
    lateinit var adapter: NewsListAdapter

    private lateinit var binding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.articlesRecyclerView.adapter = adapter
        binding.shimmerLayout.shimmerView.startShimmer()

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
        lifecycle.addObserver(viewModel)

        viewModel.articles.observe(this, {
            binding.shimmerLayout.shimmerView.stopShimmer()
            binding.shimmerLayout.shimmerView.visibility = View.GONE
            adapter.submitList(it)
        })
        listener.onClickArticle.observe(this, {
            navigateToNewsDetail(it)
        })
    }

    private fun navigateToNewsDetail(item: ArticleItem) {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra(NewsDetailActivity.EXTRA_ARTICLE, item.id)
        startActivity(intent)
    }
}
