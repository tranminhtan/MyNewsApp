package com.news.app.ui.list

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.news.app.BR
import com.news.app.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
        binding.setVariable(BR.vm, viewModel)
        binding.setVariable(BR.adapter, NewsListAdapter())
    }
}
