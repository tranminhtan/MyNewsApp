package com.news.app.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.news.app.databinding.ActivityNewsListBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = NewsListAdapter()
        binding.articlesRecyclerView.adapter = adapter
        binding.shimmerLayout.shimmerView.startShimmer()

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
        lifecycle.addObserver(viewModel)

        viewModel.articles.observe(this, {
            binding.shimmerLayout.shimmerView.stopShimmer()
            binding.shimmerLayout.shimmerView.visibility = View.GONE
            adapter.submitList(it)
        })
    }
}
