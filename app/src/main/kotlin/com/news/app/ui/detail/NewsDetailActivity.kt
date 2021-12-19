package com.news.app.ui.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.news.app.BR
import com.news.app.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NewsDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        const val EXTRA_ARTICLE = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: pass the data to VM with SavedStateHandle
        val id: Int = intent.extras?.getInt(EXTRA_ARTICLE) ?: 0
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewsDetailViewModel::class.java)

        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)
        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel)
        viewModel.fetchArticle(id)
    }
}